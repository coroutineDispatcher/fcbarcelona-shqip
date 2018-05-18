package com.stavro_xhardha.fcbarcelonashqip.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

public class Player {
    @SerializedName("name")
    private String playername;
    @SerializedName("position")
    private String position;
    @SerializedName("jerseyNumber")
    private String shirtNumber;
    @SerializedName("dateOfBirth")
    private String dateOfBirth;
    @SerializedName("nationality")
    private String nationality;
    @SerializedName("contractUntil")
    private String contractUntil;


    public String getPlayerName() {
        return playername;
    }

    public void setPlayerName(String player) {
        this.playername = player;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(String shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContractUntil() {
        return contractUntil;
    }

    public void setContractUntil(String contractUntil) {
        this.contractUntil = contractUntil;
    }
}
