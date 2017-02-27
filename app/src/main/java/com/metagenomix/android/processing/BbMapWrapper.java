package com.metagenomix.android.processing;

import android.content.Context;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sarki on 27.02.17..
 */
public class BbMapWrapper {

    private String bbmapPath;
    private String refPath;
    private String inPath;
    private String outPath;
    private Context context;

    public BbMapWrapper(Context context, String bbmapPath, String refPath, String inPath, String outPath) {
        this.bbmapPath = bbmapPath;
        this.refPath = refPath;
        this.inPath = inPath;
        this.outPath = outPath;
        this.context = context;
    }

    public Map<String, Float> getMapQs() {
        Map<String, List<Float>> mapQScores = new HashMap<>();
        Map<String, Float> finalMapQScores = new HashMap<>();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(context.getAssets().open("graph_map_output_example.txt")));
            String line = "";
            while ((line = bf.readLine()) != null) {
                if(line.trim().length() == 0 || line.startsWith("@")) {
                    continue;
                }
                // TODO: bbMapWrapper.py; line 27; how to call utility_sam.SamLine(line)?
                //String samLine = utilitySam.SAMLine(line.trim());
                //List<Float> scores = mapQScores.get(samLine.qname);
                //scores.add(Math.round(samLine.mapq));
                //mapQScores.put(samLine.qname, scores);
            }
            for(String key: mapQScores.keySet()) {
                List<Float> keyScores = mapQScores.get(key);
                float finalScore = 0.0f;
                float sum = 0.0f;
                for(float score: keyScores) {
                    sum += score;
                }
                finalScore = (sum / keyScores.size() * 1.0f);
                finalMapQScores.put(key, finalScore);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalMapQScores;
    }
}
