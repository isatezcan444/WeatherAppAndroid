package com.example.weatherapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailMetrics(
    val aqiValue: Int,
    val humidity: Int,
    val pressure: Double,
    val windSpeed: Double,
    val cloudiness: Int
)