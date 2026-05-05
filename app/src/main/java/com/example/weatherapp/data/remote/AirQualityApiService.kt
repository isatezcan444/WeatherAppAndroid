package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.AirQualityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityApiService {
    companion object {
        const val PARAM_AQI = "european_aqi"
    }

    @GET("v1/air-quality")
    suspend fun getAirQuality(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current") current: String = PARAM_AQI,
        @Query("hourly") hourly: String = PARAM_AQI,
        @Query("timezone") timezone: String = "auto"
    ): AirQualityDto
}