package com.metagenomix.android.processing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.metagenomix.android.MetagenomixApplication;
import com.metagenomix.android.activities.ConversionActivity;
import com.metagenomix.android.model.DNASegment;
import com.metagenomix.android.model.DNASequence;
import com.metagenomix.android.util.MetagenomixUtil;

import java.util.List;
import java.util.Queue;

/**
 * Created by filipfloreani on 05/12/2016.
 */

public class QueueFeeder implements Runnable {

    public static final int MSG_PROGRESS = 0;

    private static final int SEGMENTS_TO_PROCESS = SequenceConverter.getSegmentsConverted();
    private static List<DNASequence> sequenceList = SequenceConverter.getSequenceList();
    private static Queue<DNASegment> segmentQueue = MetagenomixApplication.getSegmentQueue();

    private Handler handler;

    public QueueFeeder(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        int processed = 0;

        while (processed < SEGMENTS_TO_PROCESS) {
            DNASequence dnaSequence = findSequence();
            if (dnaSequence == null) break;

            // Find a segment and add it to the queue
            DNASegment dnaSegment = findSegment(dnaSequence);
            segmentQueue.add(dnaSegment);

            // Increment the processed segments counters
            dnaSequence.incrementProcessed();
            processed++;
            // If the current sequence has been fully processed by this step, remove it from the list
            if (dnaSequence.isProcessed()) sequenceList.remove(dnaSequence);

            sendProcessedMessage(processed);
            if (ConversionActivity.DEBUG_FLAG)
                Log.d("SegmentQueue", "Left to process: " + (SEGMENTS_TO_PROCESS - processed));
            /* SLEEP */
            try {
                long randomSleepTime = MetagenomixUtil.getRandomLongWithLimit(3 * 1000);
                Thread.sleep(randomSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MetagenomixApplication.logSegmentQueue();

        ConversionActivity.isQueued = true;
    }

    private void sendProcessedMessage(int processed) {
        Message msg = handler.obtainMessage();
        Bundle b = new Bundle();
        b.putInt("msgtype", MSG_PROGRESS);
        b.putInt("processed", processed);
        msg.setData(b);
        handler.handleMessage(msg);
    }

    /**
     * Returns an unused DNA sequence or null if all sequences have been processed.
     *
     * @return An unused {@linkplain DNASequence} or {@code null} if all sequences have been processed
     */
    private DNASequence findSequence() {
        DNASequence sequence;

        if (sequenceList.isEmpty()) return null;

        do {
            int randomSequenceIndex = MetagenomixUtil.getRandomIntWithLimit(sequenceList.size());
            sequence = sequenceList.get(randomSequenceIndex);
        } while (sequence.isProcessed());

        return sequence;
    }

    /**
     * Returns a random unqueued segment from the given DNA sequence.
     *
     * @param sequence DNA sequence from which to fetch a segment
     * @return Random unqueued segment from the given DNA sequence
     */
    private DNASegment findSegment(DNASequence sequence) {
        DNASegment segment;

        boolean unuqueuedSegmentFound = false;
        do {
            int randomSegmentIndex = MetagenomixUtil.getRandomIntWithLimit(sequence.getSegments().size());

            segment = sequence.getSegments().valueAt(randomSegmentIndex);
            if (!segment.wasQueued()) {
                segment.setQueued();
                unuqueuedSegmentFound = true;
            }
        } while (!unuqueuedSegmentFound);

        return segment;
    }
}
