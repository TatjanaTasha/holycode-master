package com.holycode.github.data.remote.model

data class APIError(val code: Int, val value: String?) : Error(value)