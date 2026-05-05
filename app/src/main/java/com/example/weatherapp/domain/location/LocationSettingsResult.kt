package com.example.weatherapp.domain.location

import com.google.android.gms.common.api.ResolvableApiException

sealed class LocationSettingsResult {
    object Allowed : LocationSettingsResult()
    data class ResolutionRequired(val exception: ResolvableApiException) : LocationSettingsResult()
    data class Failed(val exception: Exception) : LocationSettingsResult()
}