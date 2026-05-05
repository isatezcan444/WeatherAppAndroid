package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.domain.model.WeatherDomainModel

interface WeatherRepository {
    suspend fun getWeatherData(locationName: String, latitude: Double, longitude: Double): WeatherDomainModel
    fun getDailyForecastByDate(date: String): DailyForecast
    suspend fun toggleFavorite(location: WeatherLocation)
    suspend fun searchCity(name: String): List<WeatherLocation>
}