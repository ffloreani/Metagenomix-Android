package com.metagenomix.android.activities;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    public static final boolean DEBUG_FLAG = true;

    private boolean isConverted = false;
    private Handler feederHandler;

    @BindView(R.id.status)
    TextView statusView;
    @BindView(R.id.convert)
    Button convertButton;
    @BindView(R.id.queue)
    Button queueButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

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
            showAlertConverted();
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

    private void showAlertConverted() {
        AlertDialog.Builder alertBuilder = MetagenomixUtil.buildOkDialog(this);
        alertBuilder.setMessage(R.string.convert_warning).show();
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
