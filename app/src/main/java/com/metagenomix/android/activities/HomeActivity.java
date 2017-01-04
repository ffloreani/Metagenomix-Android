package com.metagenomix.android.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.metagenomix.android.R;
import com.metagenomix.android.processing.SequenceConverter;

public class HomeActivity extends AppCompatActivity {

    private TextView statusView;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        statusView = (TextView) findViewById(R.id.status);
        statusView.setText(R.string.ready_status);

        startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask task = new ConverterTask().execute();
            }
        });
    }

    private class ConverterTask extends AsyncTask<Void, Void, Boolean> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage(getString(R.string.converting));
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return SequenceConverter.generateSequenceList();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            dialog.dismiss();
            statusView.setText(R.string.converted);
        }
    }
}
