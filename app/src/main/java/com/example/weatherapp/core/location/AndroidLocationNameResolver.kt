package com.example.weatherapp.core.location

import android.content.Context
import android.location.Geocoder
import com.example.weatherapp.domain.location.LocationNameResolver
import com.example.weatherapp.domain.model.WeatherLocation
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class AndroidLocationNameResolver @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationNameResolver {

    private val geocoder by lazy {
        Geocoder(context, Locale.getDefault())
    }

    override suspend fun resolve(
        latitude: Double,
        longitude: Double
    ): WeatherLocation = withContext(Dispatchers.IO) {
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            val address = addresses?.firstOrNull()

            val city = address?.locality
                ?: address?.adminArea
                ?: "Unknown"

            val country = address?.countryName ?: ""

            WeatherLocation(
                name = city,
                region = country,
                latitude = latitude,
                longitude = longitude
            )

        } catch (e: Exception) {
            WeatherLocation(
                name = "Unknown",
                region = "",
                latitude = latitude,
                longitude = longitude
            )
        }
    }
}