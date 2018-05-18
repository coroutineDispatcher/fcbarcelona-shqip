package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by stavro_xhardha on 23/04/2018.
 */

public class ResultResponse<T> {
    @SerializedName("season")
    private String year;
    @SerializedName("count")
    private String count;
    private ArrayList<T> fixtures;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<T> getFixtures() {
        return fixtures;
    }

    public void setFixtures(ArrayList<T> fixtures) {
        this.fixtures = fixtures;
    }
}
