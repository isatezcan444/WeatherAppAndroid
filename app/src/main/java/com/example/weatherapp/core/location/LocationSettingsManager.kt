package com.example.weatherapp.core.location

import android.content.Context
import com.example.weatherapp.domain.location.LocationSettingsResult
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LocationSettingsManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun checkLocationSettings(): LocationSettingsResult {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            1000 * 60 * 30
        ).setMaxUpdates(1).build()

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(context)

        return try {
            client.checkLocationSettings(builder.build()).await()
            LocationSettingsResult.Allowed
        } catch (error: Exception) {

            when (error) {
                is ResolvableApiException ->
                    LocationSettingsResult.ResolutionRequired(error)

                else ->
                    LocationSettingsResult.Failed(error)
            }
        }
    }
}