package com.holycode.github.domain.model.repo

import com.google.gson.annotations.SerializedName

data class Tree (
	@SerializedName("sha") val sha : String,
	@SerializedName("url") val url : String?
)