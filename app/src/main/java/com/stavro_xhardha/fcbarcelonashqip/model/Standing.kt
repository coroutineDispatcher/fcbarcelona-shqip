package com.stavro_xhardha.fcbarcelonashqip.model

import com.google.gson.annotations.SerializedName

/**
 * Created by stavro_xhardha on 21/04/2018.
 */

class Standing(@SerializedName("teamName")
               var teamName: String? = null,
               @SerializedName("points")
               var points: Int = 0,
               @SerializedName("goalDifference")
               var goalDifference: Int = 0,
               @SerializedName("playedGames")
               var matchesPlayed: String? = null,
               @SerializedName("position")
               var position: Int = 0)
