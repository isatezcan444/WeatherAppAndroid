package com.example.weatherapp.ui.features.details.state

import com.example.weatherapp.domain.model.DailyForecast

sealed interface DetailsUiState {
    object Loading : DetailsUiState
    data class Success(val locationName: String, val data: DailyForecast) : DetailsUiState
    data class Error(val message: String) : DetailsUiState
}