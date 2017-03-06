package com.metagenomix.android.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.metagenomix.android.R;
import com.metagenomix.android.model.Record;

import java.util.ArrayList;
import java.util.Map;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Record record = (Record) getIntent().getSerializableExtra("data");

        Map<String, Float> map = record.getMap();
        PieChart pieChart = (PieChart) findViewById(R.id.idPieChart);
        addDataSet(pieChart, map);
    }

    private void addDataSet(PieChart pieChart, Map<String, Float> map) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        for (Map.Entry<String, Float> entry : map.entrySet()) {
            pieEntries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
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
        pieChart.setData(pieData);
    }
}
