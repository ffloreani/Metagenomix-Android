package com.metagenomix.android.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.metagenomix.android.R;
import com.metagenomix.android.processing.QueueFeeder;
import com.metagenomix.android.processing.SequenceConverter;
import com.metagenomix.android.util.MetagenomixUtil;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConversionActivity extends AppCompatActivity {

    public static final boolean DEBUG_FLAG = true;

    public static boolean isQueued = true;
    public boolean isConverted = false;
    private Handler feederHandler;

    public native void map(String sampleName, String databaseName, String outputPath);

    @BindView(R.id.status)
    TextView statusView;
    @BindView(R.id.convert)
    Button convertButton;
    @BindView(R.id.queue)
    Button queueButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.results)
    Button resultsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initHandler();

        statusView.setText(R.string.ready_status);

        progressBar.setVisibility(View.INVISIBLE);
    }

    private void initHandler() {
        feederHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int msgType = msg.getData().getInt("msgtype");

                if (msgType == QueueFeeder.MSG_PROGRESS) {
                    int processed = msg.getData().getInt("processed");
                    progressBar.setProgress(processed);
                }
            }
        };
    }

    @OnClick(R.id.convert)
    public void onConvertClick() {
        new ConverterTask().execute();
    }

    @OnClick(R.id.queue)
    public void onQueueClick() {
        if (!isConverted) {
            showAlertConverted(getString(R.string.convert_warning));
            return;
        }

        Thread thread = new Thread(new QueueFeeder(feederHandler));
        thread.start();

        statusView.setText(R.string.queueing_start);

        progressBar.setProgress(0);
        progressBar.setMax(SequenceConverter.getSegmentsConverted());
        progressBar.setVisibility(View.VISIBLE);

        queueButton.setEnabled(false);
    }

    @OnClick(R.id.results)
    public void onResultsClick() {
        // copy files from /assets to internal storage folder
        try {
            MetagenomixUtil.copyDirOrFileFromAssetManager("", "Metagenomix");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        final File localStorageDir = getDir("Metagenomix", Context.MODE_PRIVATE);

        final File sampleLocal = new File(localStorageDir, "environment_sample.txt");
        final File databaseLocal = new File(localStorageDir, "database.txt");
        final File outputLocal = new File(localStorageDir, "minimap_out.txt");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                map(sampleLocal.getPath(), databaseLocal.getPath(), outputLocal.getPath());
            }
        });
        thread.start();
    }

    private void showAlertConverted(String message) {
        AlertDialog.Builder alertBuilder = MetagenomixUtil.buildOkDialog(this);
        alertBuilder.setMessage(message).show();
    }

    private class ConverterTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return SequenceConverter.generateSequenceList();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressBar.setVisibility(View.INVISIBLE);
            progressBar.setIndeterminate(false);
            statusView.setText(R.string.converted);

            isConverted = true;

            if (DEBUG_FLAG) SequenceConverter.logSequenceList();
            convertButton.setEnabled(false);
        }
    }
}
