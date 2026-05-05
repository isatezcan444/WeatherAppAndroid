package com.example.weatherapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable data object OnboardingScreen : Routes()
    @Serializable data object SearchLocationScreen : Routes()
    @Serializable data object HomeScreen : Routes()
    @Serializable data class DetailsScreen(val locationName: String, val date: String) : Routes()
    @Serializable data object SettingsScreen : Routes()
}