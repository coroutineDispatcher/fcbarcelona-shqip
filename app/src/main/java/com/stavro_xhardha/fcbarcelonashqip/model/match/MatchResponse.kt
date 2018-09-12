package com.stavro_xhardha.fcbarcelonashqip.model.match

import com.google.gson.annotations.SerializedName

data class MatchResponse(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("filters")
	val filters: Filters? = null,

	@field:SerializedName("matches")
	val matches: List<MatchesItem?>? = null
)