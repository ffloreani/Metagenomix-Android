package com.metagenomix.android.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.metagenomix.android.R;
import com.metagenomix.android.model.Record;
import com.metagenomix.android.processing.MinimapProcessing;
import com.metagenomix.android.util.DatabaseManager;
import com.metagenomix.android.util.RecursiveFileObserver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LoadDataActivity extends AppCompatActivity {

    PieChart pieChart;
    DatabaseManager db = new DatabaseManager(this);

    public static FileObserver observer;
    public long timeLastModified;
    public MinimapProcessing processing;
    public Map<String, Float> scores;
    public TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        File internalStorageDir = this.getDir("Metagenomix", Context.MODE_PRIVATE);
        //Edit this string to observer from graph map output
        String filename = "/minimap_out.txt";

        try {
            processing = new MinimapProcessing(this);
            scores = processing.process();
        } catch (Exception e) {
            Log.e("login activity", "File not found: " + e.toString());
        }
        timeLastModified = System.currentTimeMillis();
        updateGraph();

        observer =
                new RecursiveFileObserver(this.getFilesDir().toString() + filename) {
                    @Override
                    public void onEvent(int event, String file) {
                        if (event == FileObserver.MODIFY) {
                            updateGraph();
                            timeLastModified = System.currentTimeMillis();
                        }
                    }
                };
        observer.startWatching();

        setRepeatingAsyncTask();
    }

    private void setRepeatingAsyncTask() {

        final Handler handler = new Handler();
        Timer timer = new Timer();

        task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            long time = System.currentTimeMillis();
                            if (time - timeLastModified > 10000) {
                                try {
                                    mappingEnded();
                                } catch (IOException exc) {
                                    exc.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };

        timer.schedule(task, 0, 1000);  // interval of 1 second
    }

    private void updateGraph() {
        pieChart = (PieChart) findViewById(R.id.idPieChart);
        addDataSet(pieChart);
    }

    private void mappingEnded() throws IOException {
        task.cancel();
        Log.e("Mapping ended", "Final results shown and to be saved");
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        Record record = new Record();
        record.setDate(timeStamp);
        record.setMap(scores);
        db.addRecord(record);

    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        for (Map.Entry<String, Float> entry : scores.entrySet()) {
            yEntrys.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.BLUE);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);

        PieData pieData = new PieData(pieDataSet);

        pieData.setValueFormatter(new PercentFormatter());
        pieChart.setData(pieData);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
