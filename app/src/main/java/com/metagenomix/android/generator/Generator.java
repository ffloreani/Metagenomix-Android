package com.metagenomix.android.generator;

/**
 * Created by filipfloreani on 05/12/2016.
 */

public class Generator {

    private static Generator instance;

    private boolean isGeneratorRunning = false;

    public static Generator getInstance() {
        if (instance == null) {
            instance = new Generator();
        }

        return instance;
    }

    public boolean isGeneratorRunning() {
        return isGeneratorRunning;
    }

    public void startGenerator() {
        if (isGeneratorRunning) return;

        // start cron job (Handler)
        isGeneratorRunning = true;
    }

    public void stopGenerator() {
        if (!isGeneratorRunning) return;
        // Stop cron job (Handler)

        isGeneratorRunning = false;
        resetGenerator();
    }

    private void resetGenerator() {
    }
}
