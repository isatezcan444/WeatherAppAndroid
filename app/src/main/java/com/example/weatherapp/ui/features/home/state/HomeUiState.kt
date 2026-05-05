package com.example.weatherapp.ui.features.home.state

import com.example.weatherapp.domain.model.WeatherDomainModel

sealed interface HomeUiState {
    object Loading: HomeUiState
    data class Success(val data: WeatherDomainModel): HomeUiState
    data class Error(val message: String): HomeUiState
}