package com.example.weatherapp.ui.common.extensions

import androidx.compose.ui.graphics.Color
import com.example.weatherapp.R
import com.example.weatherapp.ui.features.details.state.AqiUiState

fun toAirQualityUiState(aqi: Int): AqiUiState {
    return when (aqi) {
        in 0..20 -> AqiUiState(
            titleRes = R.string.aqi_title_good,
            descriptionRes = R.string.aqi_desc_good,
            color = Color(0xFF66BB6A),
            value = aqi
        )
        in 21..40 -> AqiUiState(
            titleRes = R.string.aqi_title_fair,
            descriptionRes = R.string.aqi_desc_fair,
            color = Color(0xFF9CCC65),
            value = aqi
        )
        in 41..60 -> AqiUiState(
            titleRes = R.string.aqi_title_moderate,
            descriptionRes = R.string.aqi_desc_moderate,
            color = Color(0xFFFFB300),
            value = aqi
        )
        in 61..80 -> AqiUiState(
            titleRes = R.string.aqi_title_poor,
            descriptionRes = R.string.aqi_desc_poor,
            color = Color(0xFFFF8A65),
            value = aqi
        )
        in 81..100 -> AqiUiState(
            titleRes = R.string.aqi_title_very_poor,
            descriptionRes = R.string.aqi_desc_very_poor,
            color = Color(0xFFE57373),
            value = aqi
        )
        else -> AqiUiState(
            titleRes = R.string.aqi_title_hazardous,
            descriptionRes = R.string.aqi_desc_hazardous,
            color = Color(0xFFBA68C8),
            value = aqi
        )
    }
}