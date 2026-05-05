package com.example.weatherapp.data.repository

import com.example.weatherapp.data.local.datastore.AppPreferenceManager
import com.example.weatherapp.data.mapper.toDomainModel
import com.example.weatherapp.data.remote.AirQualityApiService
import com.example.weatherapp.data.remote.GeocodingApiService
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.domain.model.WeatherDomainModel
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApiService,
    private val geocodingApi: GeocodingApiService,
    private val airQualityApi: AirQualityApiService,
    private val preferenceManager: AppPreferenceManager
): WeatherRepository {
    private var cachedWeatherData: WeatherDomainModel? = null

    override suspend fun getWeatherData(
        locationName: String,
        latitude: Double,
        longitude: Double
    ): WeatherDomainModel = withContext(Dispatchers.IO) {
        coroutineScope {
            val weatherDeferred = async { weatherApi.getWeatherData(latitude, longitude) }
            val airQualityDeferred = async { airQualityApi.getAirQuality(latitude, longitude) }

            val weatherDto = weatherDeferred.await()
            val airQualityDto = airQualityDeferred.await()

            val location = WeatherLocation(
                name = locationName.substringBefore(",").trim(),
                region = locationName.substringAfter(",", "").trim(),
                latitude = latitude,
                longitude = longitude
            )

            val result = weatherDto.toDomainModel(
                locationName = locationName,
                location = location,
                airQuality = airQualityDto
            )

            cachedWeatherData = result
            result
        }
    }

    override fun getDailyForecastByDate(date: String): DailyForecast {
        return cachedWeatherData?.dailyForecast?.find { it.date == date }
            ?: throw Exception("No weather data was found for the selected date.")
    }

    override suspend fun toggleFavorite(location: WeatherLocation) {
        withContext(Dispatchers.IO) {
            preferenceManager.toggleFavoriteLocation(location)
        }
    }

    override suspend fun searchCity(name: String): List<WeatherLocation> = withContext(
        Dispatchers.IO) {
        try {
            val response = geocodingApi.searchCity(cityName = name)
            response.results?.map { it.toDomainModel() } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}