package com.example.weatherapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDomainModel(
    val date: String,
    val location: WeatherLocation,
    val locationName: String,
    val currentTemperature: Double,
    val hourlyForecast: List<HourlyForecast>,
    val dailyForecast: List<DailyForecast>,
    val weatherCode: Int
)

@Serializable
data class HourlyForecast(
    val time: String,
    val temperature: Double,
    val weatherCode: Int
)

@Serializable
data class DailyForecast(
    val date: String,
    val temperature: Double,
    val weatherCode: Int,
    val hourlyForecast: List<HourlyForecast>,
    val detailMetrics: WeatherDetailMetrics
)