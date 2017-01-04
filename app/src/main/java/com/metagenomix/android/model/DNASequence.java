package com.metagenomix.android.model;

import android.util.SparseArray;

/**
 * Created by filipfloreani on 18/12/2016.
 */

public class DNASequence {

    private String giCode;
    private String organismName;
    private SparseArray<DNASegment> segments;

    public DNASequence() {
        giCode = "";
        organismName = "";
        segments = new SparseArray<>();
    }

    public DNASequence(String giCode, String organismName, SparseArray<DNASegment> segments) {
        this.giCode = giCode;
        this.organismName = organismName;
        this.segments = segments;
    }

    public String getOrganismName() {
        return organismName;
    }

    public void setOrganismName(String organismName) {
        this.organismName = organismName;
    }

    public SparseArray<DNASegment> getSegments() {
        return segments;
    }

    public void setSegments(SparseArray<DNASegment> segments) {
        this.segments = segments;
    }

    public String getGiCode() {
        return giCode;
    }

    public void setGiCode(String giCode) {
        this.giCode = giCode;
    }

    public void parseFASTAHeader(String sequenceHeader) {
        int giStartIndex = sequenceHeader.indexOf('|') + 1;
        int giEndIndex = sequenceHeader.indexOf('|', giStartIndex);
        giCode = sequenceHeader.substring(giStartIndex, giEndIndex);

        int organismNameStartIndex = sequenceHeader.lastIndexOf('|') + 1;
        organismName = sequenceHeader.substring(organismNameStartIndex);
    }
}
