package com.stavro_xhardha.fcbarcelonashqip.model.ranking

import com.google.gson.annotations.SerializedName
import com.stavro_xhardha.fcbarcelonashqip.model.Team

class TableItem {

    @SerializedName("goalsFor")
    var goalsFor: Int = 0

    @SerializedName("lost")
    var lost: Int = 0

    @SerializedName("won")
    var won: Int = 0

    @SerializedName("playedGames")
    var playedGames: Int = 0

    @SerializedName("position")
    var position: Int = 0

    @SerializedName("team")
    var team: Team? = null

    @SerializedName("draw")
    var draw: Int = 0

    @SerializedName("goalsAgainst")
    var goalsAgainst: Int = 0

    @SerializedName("goalDifference")
    var goalDifference: Int = 0

    @SerializedName("points")
    var points: Int = 0

    override fun toString(): String {
        return "TableItem{" +
                "goalsFor = '" + goalsFor + '\''.toString() +
                ",lost = '" + lost + '\''.toString() +
                ",won = '" + won + '\''.toString() +
                ",playedGames = '" + playedGames + '\''.toString() +
                ",position = '" + position + '\''.toString() +
                ",team = '" + team + '\''.toString() +
                ",draw = '" + draw + '\''.toString() +
                ",goalsAgainst = '" + goalsAgainst + '\''.toString() +
                ",goalDifference = '" + goalDifference + '\''.toString() +
                ",points = '" + points + '\''.toString() +
                "}"
    }
}