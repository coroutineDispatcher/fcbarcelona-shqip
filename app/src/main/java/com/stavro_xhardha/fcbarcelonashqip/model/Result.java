package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stavro_xhardha on 23/04/2018.
 */

public class Result {
    @SerializedName("goalsHomeTeam")
    private String goalsHometeam;
    @SerializedName("goalsAwayTeam")
    private String goalsAwayTeam;

    public String getGoalsHometeam() {
        return goalsHometeam;
    }

    public void setGoalsHometeam(String goalsHometeam) {
        this.goalsHometeam = goalsHometeam;
    }

    public String getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(String goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }
}
