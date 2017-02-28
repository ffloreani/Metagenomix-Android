package com.metagenomix.android.processing;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

    public Map<Integer, List<List<Double>>> rescaleSamScoreNU(Map<Integer, List<List<Double>>> NU, double maxScore, double minScore) {
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

    public class Reads {
        Map<Integer, List<List<Double>>> NU;
        Map<Integer, List<Double>> U;
        Map<String, Integer> hRefId;
        Map<String, Integer> hReadId;
        List<String> genomes;
        List<String> read;
        Map<String, Map<String, List<Double>>> coverage;

        public Reads(double pScoreCutOff) {
            convAlig2GRmat(pScoreCutOff);
        }

        public void convAlig2GRmat(double pScoreCutOff) {
            Map<Integer, List<List<Double>>> NU = new HashMap<>();
            Map<Integer, List<Double>> U = new HashMap<>();
            Map<String, Integer> hRefId = new HashMap<>();
            Map<String, Integer> hReadId = new HashMap<>();
            List<String> genomes = new ArrayList<>();
            List<String> read = new ArrayList<>();
            Map<String, Map<String, List<Double>>> coverage = new HashMap<>();

            int brojac = 0;
            int brojac1 = 0;
            int cn = 0;

            int gCnt = 0;
            int rCnt = 0;

            double maxScore = 0.0;
            double minScore = 0.0;

            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(context.getAssets().open("graph_map_output_example.txt")));
                String line = "";
                while ((line = bf.readLine()) != null) {
                    if (line.trim().length() == 0 || line.startsWith("@")) {
                        continue;
                    }
                    List<String> l = new ArrayList<String>(Arrays.asList(line.split("\\t")));
                    String readId = l.get(0);
                    if(Integer.parseInt(l.get(1)) == 4) {
                        continue;
                    }
                    String refId = l.get(2);
                    if(refId.equals('*')) {
                        continue;
                    }
                    String ref = refId;
                    List<String> refIdList = new ArrayList<String>(Arrays.asList(refId.split("|")));
                    String ars = refIdList.get(3);

                    //TODO: bbMapWrapper.py; line 103:
                    //UTSam ut_sam = utility_sam.SAMLine(line);
                    //int readL = ut_sam.CalcReferenceLengthFromCigar()

                    int markerL = Integer.parseInt(refIdList.get(refIdList.size() - 1));
                    String gi = refIdList.get(1);
                    cn += 1;

                    List<String> arsList = new ArrayList<String>(Arrays.asList(ars.split(",")));
                    for(String c: arsList) {
                        String refId2 = "ti|" + c;
                        double pScore = findEntryScore(l, pScoreCutOff);
                        boolean skipFlag = findEntryScoreSkipFlag(pScore, pScoreCutOff);
                        if(skipFlag){
                            continue;
                        }
                        if(new Double(maxScore) == null | pScore > maxScore) {
                            maxScore = pScore;
                        }
                        if(new Double(minScore) == null | pScore < minScore) {
                            minScore = pScore;
                        }
                        Integer gIdx;
                        if(hRefId.get(refId2) != null) {
                            gIdx = hRefId.get(refId2);
                        } else {
                            gIdx = -1;
                        }
                        Map<String, List<Double>> ds = coverage.get(refId2);
                        if(ds.isEmpty()) {
                            ds = new HashMap<>();
                        }
                        List<Double> ls = ds.get(gi);
                        if(ls.isEmpty()) {
                            ls = new ArrayList<>();
                        }
                        ds.put(refId2, ls);
                        coverage.put(refId2, ds);

                        if(gIdx == -1) {
                            gIdx = gCnt;
                            hRefId.put(refId2, gIdx);
                            genomes.add(refId2);
                            gCnt += 1;
                        }
                        int rIdx = hReadId.get(readId);
                        if(new Integer(rIdx) == null) {
                            rIdx = -1;
                        }
                        if(rIdx == -1) {
                            rIdx = rCnt;
                            hReadId.put(readId, rIdx);
                            read.add(readId);
                            rCnt += 1;
                            List<Double> URIdx = new ArrayList<>();
                            URIdx.add((double) gIdx);
                            URIdx.add(pScore);
                            URIdx.add(pScore);
                            URIdx.add(pScore);
                            U.put(rIdx, URIdx);
                        } else {
                            if(U.containsKey(rIdx)) {
                                if (U.get(rIdx).get(0) == (double) gIdx) {
                                    continue;
                                }
                                List<List<Double>> NUList = new ArrayList<>();
                                NUList.add(U.get(rIdx));
                                NU.put(rIdx, NUList);
                                U.remove(rIdx);
                            }
                            if(NU.get(rIdx).get(0).contains((double) gIdx)) {
                                continue;
                            }
                            NU.get(rIdx).get(0).add((double) gIdx);
                            NU.get(rIdx).get(1).add(pScore);

                            if(pScore > NU.get(rIdx).get(3).get(0)) {
                                NU.get(rIdx).get(3).set(0, pScore);
                            }
                        }
                    }
                }
                if(new Double(maxScore) != null && new Double(minScore) != null) {
                    U = rescaleSamScoreU(U, maxScore, minScore);
                    NU = rescaleSamScoreNU(NU, maxScore, minScore);
                }
                for(int rIdx: U.keySet()) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.U = U;
            this.NU = NU;
            this.genomes = genomes;
            this.read = read;
            this.coverage = coverage;
        }

        public Map<Integer, List<List<Double>>> getNU() {
            return this.NU;
        }

        public Map<Integer, List<Double>> getU() {
            return this.U;
        }

        public Map<String, Integer> gethRefId() {
            return this.hRefId;
        }

        public Map<String, Integer> gethReadId() {
            return this.gethReadId();
        }

        public List<String> getGenomes() {
            return this.genomes;
        }

        public List<String> getRead() {
            return this.read;
        }

        public Map<String, Map<String, List<Double>>> getCoverage() {
            return this.coverage;
        }

    }
}
