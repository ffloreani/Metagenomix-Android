package com.metagenomix.android.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sarki on 09.12.16..
 */
public class Record implements Serializable {
    private String date;
    private Map<String, Float> map;

    public Record() {
    }

    public Record(String date, Map<String, Float> map) {
        this.date = date;
        this.map = map;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMap(Map<String, Float> map) {
        this.map = map;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Float> getMap() {
        return map;
    }
}
