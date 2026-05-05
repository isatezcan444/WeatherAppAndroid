package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("current") val current: CurrentWeather,
    @SerializedName("hourly") val hourly: HourlyWeather,
    @SerializedName("daily") val daily: DailyWeather
)

data class CurrentWeather(
    @SerializedName("time") val time: String,
    @SerializedName("weather_code") val weatherCode: Int,
    @SerializedName("temperature_2m") val temperature: Double,
    @SerializedName("relative_humidity_2m") val humidity: Int,
    @SerializedName("wind_speed_10m") val windSpeed: Double,
    @SerializedName("surface_pressure") val pressure: Double,
    @SerializedName("cloud_cover") val cloudiness: Int,
    @SerializedName("european_aqi") val aqi: Int
)

data class HourlyWeather(
    @SerializedName("time") val time: List<String> = emptyList(),
    @SerializedName("temperature_2m") val temperatures: List<Double> = emptyList(),
    @SerializedName("weather_code") val weatherCodes: List<Int> = emptyList()
)

data class DailyWeather(
    @SerializedName("time") val time: List<String> = emptyList(),
    @SerializedName("weather_code") val weatherCodes: List<Int> = emptyList(),
    @SerializedName("temperature_2m_min") val minTemperatures: List<Double> = emptyList(),
    @SerializedName("temperature_2m_max") val maxTemperatures: List<Double> = emptyList(),
    @SerializedName("relative_humidity_2m_max") val humidity: List<Double> = emptyList(),
    @SerializedName("wind_speed_10m_max") val windSpeed: List<Double> = emptyList(),
    @SerializedName("surface_pressure_max") val pressure: List<Double> = emptyList(),
    @SerializedName("cloud_cover_max") val cloudCover: List<Int> = emptyList()
)