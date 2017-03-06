package com.metagenomix.android.processing;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sarki on 06.03.17..
 */
public class MinimapProcessing {

    private Context context;

    public MinimapProcessing(Context context) {
        this.context = context;
    }

    public Map<String, Float> process() {
        Map<String, Float> scores = new HashMap<>();
        try {
            File localDir = context.getDir("Metagenomix", Context.MODE_PRIVATE);
            String output = new File(localDir, "minimap_out.txt").getAbsolutePath();
            BufferedReader bf = new BufferedReader(new FileReader(output));

            String line;
            while ((line = bf.readLine()) != null) {
                List<String> lineAsList = splitLine(line);
                String queryName = getQueryName(lineAsList.get(0));
                System.out.println(lineAsList);
                float probabilty = getMatchProbability(Integer.parseInt(lineAsList.get(6)), Integer.parseInt(lineAsList.get(9)));
                scores.put(queryName, probabilty);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return scores;
    }

    private List<String> splitLine(String line) {
        return new ArrayList<>(Arrays.asList(line.split("\\t")));
    }

    public String getQueryName(String queryName) {
        List<String> l = new ArrayList<>(Arrays.asList(queryName.split("\\|")));
        return l.get(4);
    }

    public float getMatchProbability(int dbLen, int dbMatch) {
        return (dbMatch * 1.0f / dbLen) * 100;
    }
}
