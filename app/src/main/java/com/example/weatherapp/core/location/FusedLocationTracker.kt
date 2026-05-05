package com.example.weatherapp.core.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.LocationManager
import com.example.weatherapp.domain.location.LocationResult
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.ui.common.extensions.hasPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@SuppressLint("MissingPermission")
class FusedLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    override suspend fun getCurrentLocation(): LocationResult = withContext(Dispatchers.IO) {
        val hasPermission = application.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) ||
                application.hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)

        if (!hasPermission) {
            return@withContext LocationResult.NoPermission
        }

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGpsEnabled) {
            return@withContext LocationResult.GpsDisabled
        }

        return@withContext try {
            val location = locationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).await()

            location?.let {
                LocationResult.Success(
                    WeatherLocation(
                        name = "Current Location",
                        region = "",
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                )
            } ?: LocationResult.Error()
        } catch (e: Exception) {
            LocationResult.Error(e.message)
        }
    }
}