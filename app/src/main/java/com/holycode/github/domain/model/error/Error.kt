package com.holycode.github.domain.model.error

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("message") val message: String,
    @SerializedName("documentation_url") val documentationUrl: String
)