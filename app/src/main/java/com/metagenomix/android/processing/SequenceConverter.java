package com.metagenomix.android.processing;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.metagenomix.android.MetagenomixApplication;
import com.metagenomix.android.model.DNASegment;
import com.metagenomix.android.model.DNASequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

/**
 * Processes the environment sample FASTA file located at 'res/raw/environment_sample.txt'
 * and creates DNA sequences for further processing.
 *
 * Created by filipfloreani on 05/12/2016.
 */
public abstract class SequenceConverter {
    private static int sampleID;
    private static List<DNASequence> sequenceList = new Vector<>();

    static {
        sampleID = MetagenomixApplication.getAppContext().getResources()
                .getIdentifier("environment_sample", "raw", "com.metagenomix.android");
    }

    @NonNull
    public static List<DNASequence> getSequenceList() {
        return sequenceList;
    }

    /**
     * Generates a list of {@linkplain DNASequence} based on the environment sample file.
     */
    public static boolean generateSequenceList() {
        InputStream is = MetagenomixApplication.getAppContext().getResources().openRawResource(sampleID);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        try {
            String line;
            String header = null;
            while ((line = reader.readLine()) != null) {
                if (header == null)
                    header = convertSingleSequence(reader, null, line);
                else
                    header = convertSingleSequence(reader, line, header);

                if (header == null) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                is.close();
                reader.close();
            } catch (IOException ignored) {}
        }

        return true;
    }

    /**
     * Generates a single {@linkplain DNASequence} based on the given file reader
     * and read string parameters, stores the created sequence in a local list.
     *
     * @param reader The reader to use when reading segments of the new sequence
     * @param line   Contains the first segment of the new sequence or is null if this is the first sequence to be generated
     * @param header Contains the sequence header with the organism name and GI code
     * @return Last string read from the given reader, contains the header of the next sequence or null if EOF
     * @throws IOException Routed from {@linkplain BufferedReader#readLine()}
     */
    private static String convertSingleSequence(BufferedReader reader, String line, String header) throws IOException {
        DNASequence sequence = new DNASequence();
        SparseArray<DNASegment> segmentsMap = new SparseArray<>();
        sequence.parseFASTAHeader(header);

        if (line != null) {
            DNASegment firstSegment = new DNASegment(sequence.getGiCode(), line);
            segmentsMap.append(firstSegment.hashCode(), firstSegment);
        }

        while ((line = reader.readLine()) != null) {
            if (line.contains(">")) break;
            DNASegment segment = new DNASegment(sequence.getGiCode(), line);
            segmentsMap.append(segment.hashCode(), segment);
        }
        sequence.setSegments(segmentsMap);
        sequenceList.add(sequence);

        return line;
    }
}