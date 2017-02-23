package com.metagenomix.android.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.metagenomix.android.R;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadData(View view) {
        try {
            Intent intent = new Intent(this, LoadDataActivity.class);
            writeGraphOutputTest();
            try {
                startActivity(intent);
            } catch (Exception exc) {
                Log.d(TAG, "CANT START INTENT");
            }
        } catch (Exception e) {
            Log.d(TAG, "INTENT ERROR");
        }

    }

    public void showHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void test(View view) {
        Log.d(TAG, "TEST");
        Log.d(TAG, "TEST");
    }

    private void writeGraphOutputTest() {

        try {
            FileOutputStream fos = openFileOutput("graph.txt", Context.MODE_PRIVATE);
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(fos));

            BufferedReader bf = new BufferedReader(new InputStreamReader(getAssets().open("graph_map_output_example.txt")));
            String str = "";
            while ((str = bf.readLine()) != null) {
                printWriter.println(str);
            }
            printWriter.close();
            Log.d(TAG, "successfully written file");
        } catch (Exception e) {
            Log.d(TAG, "Error while writing to file.");
        }
    }

    /*REMOVE TESTING METHODS
    private void writeToFile() {
        List<Integer> numbers = new ArrayList<>();

        //fill list with 50 random numbers in range [1,5]
        for(int i=0; i<50; i++) {
            int number = 1 + (int)(Math.random() * ((5 - 1) + 1));
            numbers.add(number);
        }
        try {
            FileOutputStream fos = openFileOutput("numbers.txt", Context.MODE_PRIVATE);
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(fos));
            for(int number: numbers) {
                printWriter.println(String.valueOf(number));
            }
            printWriter.close();
        } catch (Exception exc) {
            Log.d(TAG, "Error while writing to file.");
        }

    }*/


}
