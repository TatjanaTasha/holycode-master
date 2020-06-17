package com.holycode.github

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.formatISO8601UTC(): String? {
    val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.getDefault())
    val prettydf: DateFormat  =SimpleDateFormat( "dd. MMM yyyy.",Locale.getDefault())
    try {
        val date = df.parse(this)
       return   prettydf.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}