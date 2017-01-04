package com.metagenomix.android;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by filipfloreani on 18/12/2016.
 */

public class MetagenomixApplication extends Application {

    private static Context context;
    private static LinkedBlockingQueue<String> segmentQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        segmentQueue = new LinkedBlockingQueue<>();
    }

    public static Context getAppContext() {
        return context;
    }

    /**
     * Fetches the linked blocking queue for storing segments that
     * are ready to be processed.
     *
     * @return Queue of segments that are to be processed
     */
    public static LinkedBlockingQueue<String> getSegmentQueue() {
        return segmentQueue;
    }
}
