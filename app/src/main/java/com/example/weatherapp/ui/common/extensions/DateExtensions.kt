package com.example.weatherapp.ui.common.extensions

import android.content.Context
import java.text.SimpleDateFormat

fun String.toDayName(context: Context): String {
    val date = parseDate(this, context)
    return if (date != null) {
        val locale = context.resources.configuration.locales[0]
        val outputFormat = SimpleDateFormat("EEEE", locale)
        outputFormat.format(date)
    } else {
        this
    }
}

fun String.toLocalizedDate(context: Context): String {
    val date = parseDate(this, context)
    return if (date != null) {
        val locale = context.resources.configuration.locales[0]
        val outputFormat = SimpleDateFormat("d MMMM yyyy", locale)
        outputFormat.format(date)
    } else {
        this
    }
}

fun String.toLocalizedTime(context: Context): String {
    val date = parseDate(this, context)

    return if (date != null && this.contains("T")) {
        val locale = context.resources.configuration.locales[0]
        val outputFormat = SimpleDateFormat("HH:mm", locale)
        val formattedTime = outputFormat.format(date)
        if (formattedTime == "00:00") "" else formattedTime
    } else {
        ""
    }
}

private fun parseDate(input: String, context: Context): java.util.Date? {
    val locale = context.resources.configuration.locales[0]
    return try {
        if (input.contains("T")) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm", locale).parse(input)
        } else {
            SimpleDateFormat("yyyy-MM-dd", locale).parse(input)
        }
    } catch (e: Exception) {
        null
    }
}