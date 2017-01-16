package com.metagenomix.android.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.metagenomix.android.R;
import com.metagenomix.android.model.Record;
import com.metagenomix.android.util.RecursiveFileObserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LoadDataActivity extends AppCompatActivity {

    PieChart pieChart;
    Map<String, Float> map = new HashMap<>();
    DatabaseManager db = new DatabaseManager(this);

    public static FileObserver observer;
    public InputStream inputStream;
    public InputStreamReader inputStreamReader;
    public BufferedReader bufferedReader;
    public TimerTask task;
    public ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        //Edit this string to observer from graph map output
        String filename = "/graph.txt";

        try {
            inputStream = this.openFileInput("graph.txt");
            if (inputStream != null) {
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedReader.readLine();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        observer =
                new RecursiveFileObserver(this.getFilesDir().toString()+filename) {
                    @Override
                    public void onEvent(int event, String file) {
                        if(event == FileObserver.MODIFY){
                            updateGraph(inputStreamReader, bufferedReader);
                        }
                    }
                };
        observer.startWatching();

        setRepeatingAsyncTask();

    }

    /*
    * TODO: REMOVE WHEN ACTUAL GRAPH MAP IS IMPLEMENTED*/
    private void setRepeatingAsyncTask() {

        final Handler handler = new Handler();
        Timer timer = new Timer();

        task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            updateGraph(inputStreamReader, bufferedReader);
                        } catch (Exception e) {
                            try {
                                mappingEnded();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
            }
        };

        timer.schedule(task, 0, 2*10);  // interval of one minute
    }

    private void updateGraph(InputStreamReader is, BufferedReader bf){
        ArrayList<String> numbers = readFromFile(this, is, bf);
        populateMap(map, numbers);
        pieChart = (PieChart) findViewById(R.id.idPieChart);
        addDataSet(pieChart);
    }

    private void mappingEnded() throws IOException {

        //KILL ASYNC TASK
        task.cancel();
        Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_LONG);

        Log.e("Mapping ended", "Final results shown and to be saved");
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        Record record = new Record();
        record.setDate(timeStamp);
        record.setMap(map);
        db.addRecord(record);

        bufferedReader.close();
        inputStream.close();
    }

    public String handleLine(String line) {
        int position = line.indexOf("|ti|");
        String lineSub = line.substring(position+4, line.length());
        String finalLine = "";
        for(int i=0; i<lineSub.length(); i++) {
            if(lineSub.charAt(i) != '|') {
                finalLine += lineSub.charAt(i);
            } else {
                break;
            }
        }
        return finalLine;
    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys= new ArrayList<>();
        for(Map.Entry<String, Float> entry: map.entrySet()){
            yEntrys.add(new PieEntry(entry.getValue(), entry.getKey()));
            Log.e(entry.getKey().toString(), entry.getValue().toString());
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "data");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(android.R.color.holo_red_dark);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
    }

    private ArrayList<String> readFromFile(Context context, InputStreamReader is, BufferedReader bf) {

        ArrayList<String> numbers = new ArrayList<>();
        try {
            if (inputStream != null) {
                String line = bufferedReader.readLine();
                String[] lineArr = handleLine(line).split(",");
                for(String s: lineArr){
                    numbers.add(s);
                }
            } else {
                Log.e("NULL", "//////////////#################3///////////////");
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return numbers;
    }

    public void populateMap(Map<String, Float> map, ArrayList<String> list) {
        /*
        * TODO: ovdje napravi upit u EM ALGORITAM sto se tice imena, posalji mu broj iz liste list
        * dobij float (vjerojatnost pojave), na temelju nje odredi velicinu polja u chartu*/
        for(String number: list) {
            if(!map.containsKey(number)) {
                map.put(number,1.0f);
            } else {
                float n = map.get(number);
                n++;
                map.put(number, n);
            }
        }
    }
}
