package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

/**
 * Created by stavro_xhardha on 23/04/2018.
 */

class MatchDetails {
    @SerializedName("date")
    var date: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("matchday")
    var matchDay: String? = null
    @SerializedName("homeTeamName")
    var homeTeamNanme: String? = null
    @SerializedName("awayTeamName")
    var awayTeamName: String? = null
    @SerializedName("result")
    var result: Result? = null
}
