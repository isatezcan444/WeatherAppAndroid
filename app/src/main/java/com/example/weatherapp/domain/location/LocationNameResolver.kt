package com.example.weatherapp.domain.location

import com.example.weatherapp.domain.model.WeatherLocation

interface LocationNameResolver {
    suspend fun resolve(latitude: Double, longitude: Double): WeatherLocation
}