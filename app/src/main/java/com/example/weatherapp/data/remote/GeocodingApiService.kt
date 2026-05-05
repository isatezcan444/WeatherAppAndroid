package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.CitySearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {
    companion object {
        const val DEFAULT_LANGUAGE = "en"
        const val DEFAULT_FORMAT = "json"
        const val DEFAULT_COUNT = 5
    }

    @GET("v1/search")
    suspend fun searchCity(
        @Query("name") cityName: String,
        @Query("count") count: Int = DEFAULT_COUNT,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("format") format: String = DEFAULT_FORMAT
    ): CitySearchDto
}