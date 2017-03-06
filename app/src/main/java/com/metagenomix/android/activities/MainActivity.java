package com.metagenomix.android.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.metagenomix.android.R;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.load_data_button)
    Button loadDataButton;
    @BindView(R.id.history_button)
    Button historyButton;
    @BindView(R.id.convert_button)
    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.load_data_button)
    public void loadData() {
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

    @OnClick(R.id.history_button)
    public void showHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.convert_button)
    public void startConversionActivity() {
        Intent intent = new Intent(this, ConversionActivity.class);
        startActivity(intent);
    }

    private void writeGraphOutputTest() {

        try {
            FileOutputStream fos = openFileOutput("ispis.txt", Context.MODE_PRIVATE);
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(fos));

            BufferedReader bf = new BufferedReader(new InputStreamReader(getAssets().open("minimap_output.txt")));
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
}
