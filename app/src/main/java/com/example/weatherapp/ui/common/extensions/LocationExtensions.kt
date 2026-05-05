package com.example.weatherapp.ui.common.extensions

import com.example.weatherapp.domain.model.WeatherLocation
val WeatherLocation.fullDisplayName: String
    get() = when {
        name.isBlank() && region.isBlank() -> "Unknown"
        region.isBlank() -> name
        else -> "$name, $region"
    }