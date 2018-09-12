package com.stavro_xhardha.fcbarcelonashqip.model.match

import com.google.gson.annotations.SerializedName

data class ExtraTime(

	@field:SerializedName("awayTeam")
	val awayTeam: Any? = null,

	@field:SerializedName("homeTeam")
	val homeTeam: Any? = null
)