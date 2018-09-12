package com.stavro_xhardha.fcbarcelonashqip.model.match

import com.google.gson.annotations.SerializedName

data class RefereesItem(

	@field:SerializedName("nationality")
	val nationality: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)