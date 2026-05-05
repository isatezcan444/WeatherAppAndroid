package com.example.weatherapp.ui.features.details.state

import androidx.compose.ui.graphics.Color

data class AqiUiState(
    val titleRes: Int,
    val descriptionRes: Int,
    val color: Color,
    val value: Int
)