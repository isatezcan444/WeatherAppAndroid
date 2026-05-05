package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class CitySearchDto(
    @SerializedName("results") val results: List<CityDto> = emptyList()
)

data class CityDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("country") val country: String? = null,
    @SerializedName("admin1") val admin1: String? = null
)