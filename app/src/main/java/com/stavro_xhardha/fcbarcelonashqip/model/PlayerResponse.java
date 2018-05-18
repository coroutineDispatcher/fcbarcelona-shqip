package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

public class PlayerResponse<T> {
    @SerializedName("count")
    private int count;
    @SerializedName("players")
    private ArrayList<T> players;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<T> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<T> players) {
        this.players = players;
    }
}
