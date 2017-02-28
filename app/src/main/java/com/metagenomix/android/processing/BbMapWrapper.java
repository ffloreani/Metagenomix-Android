package com.metagenomix.android.processing;

import android.content.Context;
import android.util.FloatProperty;
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

    public double findEntryScore(List<String> ln, double pScoreCutOff) {
        int mxBitSc = 700;
        int sigma2 = 3;
        float mapq = Float.parseFloat(ln.get(4));
        float mapq2 = mapq / (-10.0f);
        double pscore = 1.0 - Math.pow(10.0, (double) mapq2);
        return pscore;
    }

    public boolean findEntryScoreSkipFlag(double pScore, double pScoreCutOff) {
        if(pScore < pScoreCutOff)
            return true;
        return false;
    }

    public Map<Integer, List<Double>> rescaleSamScoreU(Map<Integer, List<Double>> U, double maxScore, double minScore) {
        double scalingFactor;
        if (minScore < 0) {
            scalingFactor = 100.0 / (maxScore - minScore);
        } else {
            scalingFactor = 1.0 / maxScore;
        }

        for(int rIdx: U.keySet()) {
            if(minScore < 0) {
                double value = U.get(rIdx).get(1) - minScore;
                List<Double> keyList = U.get(rIdx);
                keyList.add(1, value);
                U.put(rIdx, keyList);
            }
            double val = Math.exp(U.get(rIdx).get(1) * scalingFactor);
            List<Double> list = U.get(rIdx);
            list.set(1, val);
            list.set(3, val);
            U.put(rIdx, list);
        }
        return U;
    }

    public Map<Integer, List<Double>> rescaleSamScoreNU(Map<Integer, List<List<Double>>> NU, double maxScore, double minScore) {
        double scalingFactor;
        if (minScore < 0) {
            scalingFactor = 100.0 / (maxScore - minScore);
        } else {
            scalingFactor = 1.0 / maxScore;
        }

        for(int rIdx: NU.keySet()) {

            NU.get(rIdx).get(3).set(0, 0.0);
            for(int i=0; i< NU.get(rIdx).get(1).size(); i++) {
                if(minScore < 0) {
                    NU.get(rIdx).get(1).set(i, NU.get(rIdx).get(1).get(i) - minScore);
                }
                NU.get(rIdx).get(1).set(i, Math.exp(NU.get(rIdx).get(1).get(i) * scalingFactor));
                if(NU.get(rIdx).get(1).get(i) > NU.get(rIdx).get(3).get(0)) {
                    NU.get(rIdx).get(3).set(0, NU.get(rIdx).get(1).get(i));
                }
            }
        }
        return NU;
    }
}
