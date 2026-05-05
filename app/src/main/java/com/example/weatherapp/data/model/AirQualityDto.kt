package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class AirQualityDto(
    @SerializedName("current") val current: CurrentAirQuality,
    @SerializedName("hourly") val hourly: HourlyAirQuality
)

data class CurrentAirQuality(
    @SerializedName("time") val time: String,
    @SerializedName("european_aqi") val aqi: Int,
    @SerializedName("pm2_5") val pm25: Double? = null
)

data class HourlyAirQuality(
    @SerializedName("time") val time: List<String> = emptyList(),
    @SerializedName("european_aqi") val aqiList: List<Int> = emptyList()
)