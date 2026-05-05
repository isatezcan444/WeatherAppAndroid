package com.example.weatherapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherLocation(
    val name: String,
    val region: String,
    val latitude: Double,
    val longitude: Double
)