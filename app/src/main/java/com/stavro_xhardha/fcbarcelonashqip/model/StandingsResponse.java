package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by stavro_xhardha on 21/04/2018.
 */

public class StandingsResponse<T>{
    @SerializedName("leagueCaption")
    private String leagueName;
    @SerializedName("matchday")
    private int matchDay;
    @SerializedName("standing")
    private ArrayList<T> standing;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(int matchDay) {
        this.matchDay = matchDay;
    }

    public ArrayList<T> getStanding ()
    {
        return standing;
    }

    public void setStanding (ArrayList<T> standing)
    {
        this.standing = standing;
    }
}
