package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by stavro_xhardha on 21/04/2018.
 */

class StandingsResponse<T> {
    @SerializedName("leagueCaption")
    var leagueName: String? = null
    @SerializedName("matchday")
    var matchDay: Int = 0
    @SerializedName("standing")
    var standing: ArrayList<T>? = null
}
