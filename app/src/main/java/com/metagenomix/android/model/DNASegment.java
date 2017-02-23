package com.metagenomix.android.model;

/**
 * Created by filipfloreani on 04/01/2017.
 */

public class DNASegment {

    private String sequenceID;
    private String segment;
    private boolean wasQueued = false;

    public DNASegment() {
        this("", "");
    }

    public DNASegment(String sequenceID, String segment) {
        this.sequenceID = sequenceID;
        this.segment = segment;
    }

    public String getSequenceID() {
        return sequenceID;
    }

    public void setSequenceID(String sequenceID) {
        this.sequenceID = sequenceID;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public boolean wasQueued() {
        return wasQueued;
    }

    public void setQueued() {
        wasQueued = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DNASegment segment1 = (DNASegment) o;

        return sequenceID.equals(segment1.sequenceID) && segment.equals(segment1.segment);

    }

    @Override
    public int hashCode() {
        int result = sequenceID.hashCode();
        result = 31 * result + segment.hashCode();
        return result;
    }
}
