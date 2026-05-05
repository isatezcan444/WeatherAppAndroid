package com.example.weatherapp.domain.location
import com.example.weatherapp.domain.model.WeatherLocation

sealed class LocationResult {
    data class Success(val location: WeatherLocation) : LocationResult()
    object NoPermission : LocationResult()
    object GpsDisabled : LocationResult()
    data class Error(val message: String? = null) : LocationResult()
}