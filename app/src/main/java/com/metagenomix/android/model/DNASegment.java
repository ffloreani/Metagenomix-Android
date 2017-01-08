package com.metagenomix.android.model;

import android.support.annotation.NonNull;

/**
 * Created by filipfloreani on 04/01/2017.
 */

public class DNASegment {

    @NonNull
    private String sequenceID;
    @NonNull
    private String segment;
    private boolean wasQueued = false;

    public DNASegment() {
        this("", "");
    }

    public DNASegment(@NonNull String sequenceID, @NonNull String segment) {
        this.sequenceID = sequenceID;
        this.segment = segment;
    }

    @NonNull
    public String getSequenceID() {
        return sequenceID;
    }

    public void setSequenceID(@NonNull String sequenceID) {
        this.sequenceID = sequenceID;
    }

    @NonNull
    public String getSegment() {
        return segment;
    }

    public void setSegment(@NonNull String segment) {
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
