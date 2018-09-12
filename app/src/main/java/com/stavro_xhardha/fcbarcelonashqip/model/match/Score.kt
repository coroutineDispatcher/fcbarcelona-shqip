package com.stavro_xhardha.fcbarcelonashqip.model.match

import com.google.gson.annotations.SerializedName

data class Score(

        @field:SerializedName("duration")
        val duration: String? = null,

        @field:SerializedName("winner")
        val winner: String? = null,

        @field:SerializedName("penalties")
        val penalties: Penalties? = null,

        @field:SerializedName("halfTime")
        val halfTime: HalfTime? = null,

        @field:SerializedName("fullTime")
        val fullTime: FullTime? = null,

        @field:SerializedName("extraTime")
        val extraTime: ExtraTime? = null
)