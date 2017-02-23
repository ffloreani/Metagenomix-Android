package com.metagenomix.android;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.metagenomix.android.model.DNASegment;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by filipfloreani on 18/12/2016.
 */

public class MetagenomixApplication extends Application {

    private static Context context;
    private static LinkedBlockingQueue<DNASegment> segmentQueue;

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
    public static LinkedBlockingQueue<DNASegment> getSegmentQueue() {
        return segmentQueue;
    }

    public static void logSegmentQueue() {
        for (DNASegment segment : segmentQueue) {
            Log.d("SegmentQueue", segment.getSequenceID() + ", " + segment.getSegment());
        }
        Log.d("SegmentQueue", "Finished logging segments");
    }
}
