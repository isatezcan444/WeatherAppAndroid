package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    companion object {
        private const val CURRENT_PARAMS =
            "weather_code,temperature_2m,relative_humidity_2m," +
                    "wind_speed_10m,surface_pressure,cloud_cover,european_aqi"

        private const val HOURLY_PARAMS = "weather_code,temperature_2m"

        private const val DAILY_PARAMS =
            "weather_code,temperature_2m_max,temperature_2m_min," +
                    "relative_humidity_2m_max,wind_speed_10m_max," +
                    "surface_pressure_max,cloud_cover_max"

        const val DEFAULT_FORECAST_DAYS = 7
        const val DEFAULT_TIMEZONE = "auto"
    }

    @GET("v1/forecast")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = CURRENT_PARAMS,
        @Query("hourly") hourly: String = HOURLY_PARAMS,
        @Query("daily") daily: String = DAILY_PARAMS,
        @Query("forecast_days") forecastDays: Int = DEFAULT_FORECAST_DAYS,
        @Query("timezone") timezone: String = DEFAULT_TIMEZONE
    ): WeatherDto
}