package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

/**
 * Created by stavro_xhardha on 23/04/2018.
 */

class Result {
    @SerializedName("goalsHomeTeam")
    var goalsHometeam: String? = null
    @SerializedName("goalsAwayTeam")
    var goalsAwayTeam: String? = null
}
