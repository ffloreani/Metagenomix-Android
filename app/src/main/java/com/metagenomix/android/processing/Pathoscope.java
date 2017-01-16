package com.metagenomix.android.processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by sarki on 15.01.17..
 */
public class Pathoscope {

    public void algorithm(Map<Integer, List<Integer>> U, Map<Integer, List<List<Double>>> NU, List<String> genomes,
                          int maxIter, float emEpsilon, boolean verbose, int piPrior, int thetaPrior) {

        int G = genomes.size();
        List<Double> pi = new ArrayList<>();
        List<Double> theta = new ArrayList<>();
        for(int i=0; i<G; i++){
            double result = 1/G;
            pi.add(result);
            theta.add(result);
        }

        List<Double> initPi = pi;
        List<Integer> uWeights = new ArrayList<>();
        for(int i=0; i<U.size(); i++){
            uWeights.add(U.get(i).get(1));
        }

        float maxUweights = 0;
        float Utotal = 0;

        if(uWeights.size()>0){
            maxUweights = Collections.max(uWeights);
            for(float weight: uWeights){
                Utotal += weight;
            }
        }

        List<Integer> piSum0 = new ArrayList<>();
        for(int i=0; i<G; i++){
            piSum0.add(0);
        }

        for(int i=0; i<U.size(); i++){
            int piSumSingle = piSum0.get(U.get(i).get(0));
            int index = piSum0.indexOf(U.get(i).get(0));
            piSumSingle += U.get(i).get(1);
            piSum0.set(index, piSumSingle);
        }

        List<Double> nuWeights = new ArrayList<>();
        for(double i=0; i<NU.size(); i++){
            nuWeights.add(NU.get(i).get(3).get(0));
        }

        double maxNUweights = 0.0;
        double NUTotal = 0.0;

        if(nuWeights.size() > 0){
            maxNUweights = Collections.max(nuWeights);
            for(float weight: uWeights){
                NUTotal += weight;
            }
        }

        double priorWeight = Math.max(maxUweights, maxNUweights);

        int lenNu = NU.size();
        if(lenNu == 0) {
            lenNu = 1;
        }

        for(int i=0; i<maxIter; i++){
            List<Double> piOld = pi;
            List<Double> thetaOld = theta;
            List<Double> thetaSum = new ArrayList<>();
            for(int x=0; i<genomes.size(); x++){
                thetaSum.add(0.0);
            }
            for(int j=0; j<NU.size(); j++){
                List<List<Double>> z = NU.get(j);
                List<Double> ind = z.get(0);
                List<Double> piTmp = new ArrayList<>();
                for(int k=0; k<ind.size(); k++) {
                    piTmp.add(pi.get(k));
                }

                List<Double> thetaTmp = new ArrayList<>();
                for(int k=0; k<ind.size(); k++) {
                    thetaTmp.add(pi.get(k));
                }

                List<Double> xTmp = new ArrayList<>();
                for(int k=0; k<ind.size(); k++){
                    xTmp.add(1.0 * piTmp.get(k) * thetaTmp.get(k) * z.get(1).get(k));
                }
                double xSum =0.0;
                for(int k=0; k<ind.size(); k++){
                    xSum += xTmp.get(k);
                }

                List<Double> xNorm = new ArrayList<>();

                if(xSum == 0) {
                    for(int k=0; k<xTmp.size(); k++){
                        xNorm.add(0.0);
                    }
                }  else {
                    for(int k=0; k<xTmp.size(); k++){
                        xNorm.add(1. * k / xSum);
                    }
                }
                List<List<Double>> nuList = NU.get(j);
                nuList.set(2, xNorm);
                NU.put(j, nuList);

                for(int k=0; k<ind.size(); k++){
                    int x = ind.get(k).intValue();
                    thetaSum.set(x, xNorm.get(k) * NU.get(j).get(3).get(0));
                }
            }
            List<Double> piSum = new ArrayList<>();
            for(int k=0; k<thetaSum.size(); k++){
                piSum.add(thetaSum.get(k) + piSum0.get(k));
            }
            double pip = piPrior * priorWeight;
            double totalDiv = Utotal + NUTotal;

            if(totalDiv == 0){
                totalDiv = 1;
            }
            for(int k=0; k<piSum.size(); k++){
                pi.set(k, (1. * k + pip ) / (Utotal + NUTotal + pip * piSum.size()));
            }
            if(i == 0) {
                initPi = pi;
            }

            double thetaP = thetaPrior * priorWeight;
            double NUTotalDiv = NUTotal;
            if(NUTotalDiv == 0){
                NUTotalDiv = 1;
            }
            for(int k=0; k<thetaSum.size(); k++){
                pi.set(k, (1. * k + thetaP ) / (NUTotalDiv + thetaP * thetaSum.size()));
            }

            double cutOff = 0.0;
            double thetaCutoff = 0.0;

            for(int k=0; k<pi.size(); k++){
                cutOff += Math.abs(piOld.get(k) - pi.get(k));
                thetaCutoff += Math.abs(thetaOld.get(k) - theta.get(k));
            }
            if(thetaCutoff <= emEpsilon || lenNu == 1){
                break;
            }
        }
        /* Pronaci nacin za vracanje tuplea. Mozda nova klasa koja ce drzat 3 liste i mapu?*/
    }
}
