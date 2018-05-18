package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stavro_xhardha on 23/04/2018.
 */

public class MatchDetails {
    @SerializedName("date")
    private String date;
    @SerializedName("status")
    private String status;
    @SerializedName("matchday")
    private String matchDay;
    @SerializedName("homeTeamName")
    private String homeTeamNanme;
    @SerializedName("awayTeamName")
    private String awayTeamName;
    @SerializedName("result")
    private Result result;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(String matchDay) {
        this.matchDay = matchDay;
    }

    public String getHomeTeamNanme() {
        return homeTeamNanme;
    }

    public void setHomeTeamNanme(String homeTeamNanme) {
        this.homeTeamNanme = homeTeamNanme;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
