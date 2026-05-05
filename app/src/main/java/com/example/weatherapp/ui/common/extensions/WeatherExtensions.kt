package com.example.weatherapp.ui.common.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R

@Composable
fun Int.toWeatherConditionText(): String {
    return when (this) {
        0 -> stringResource(R.string.weather_desc_clear)
        1, 2, 3 -> stringResource(R.string.weather_desc_partly_cloudy)
        45, 48 -> stringResource(R.string.weather_desc_foggy)
        51, 53, 55 -> stringResource(R.string.weather_desc_drizzle)
        61, 63, 65 -> stringResource(R.string.weather_desc_rainy)
        71, 73, 75 -> stringResource(R.string.weather_desc_snowy)
        80, 81, 82 -> stringResource(R.string.weather_desc_heavy_rain)
        95, 96, 99 -> stringResource(R.string.weather_desc_stormy)
        else -> stringResource(R.string.weather_desc_unknown)
    }
}

@Composable
fun Int.toWeatherSummaryUi(): Pair<String, String> {
    return when (this) {
        0, 1 -> stringResource(R.string.summary_title_clear) to
                stringResource(R.string.summary_desc_clear)
        2, 3 -> stringResource(R.string.summary_title_cloudy) to
                stringResource(R.string.summary_desc_cloudy)
        45, 48 -> stringResource(R.string.summary_title_foggy) to
                stringResource(R.string.summary_desc_foggy)
        51, 53, 55, 61, 63, 65, 80, 81, 82 ->
            stringResource(R.string.summary_title_rainy) to
                    stringResource(R.string.summary_desc_rainy)
        71, 73, 75 -> stringResource(R.string.summary_title_snowy) to
                stringResource(R.string.summary_desc_snowy)
        95, 96, 99 -> stringResource(R.string.summary_title_stormy) to
                stringResource(R.string.summary_desc_stormy)
        else -> stringResource(R.string.summary_title_default) to
                stringResource(R.string.summary_desc_default)
    }
}

@Composable
fun Int.toWeatherSummaryThemeColors(): Pair<Color, Color> {
    val colorScheme = MaterialTheme.colorScheme

    return when (this) {
        0 -> colorScheme.inverseSurface to colorScheme.inverseOnSurface
        1, 2, 3, 45, 48 -> colorScheme.secondaryContainer to colorScheme.onSecondaryContainer
        51, 53, 55, 61, 63, 65, 80, 81, 82 -> colorScheme.errorContainer to colorScheme.onErrorContainer
        71, 73, 75, 95, 96, 99 -> colorScheme.primaryContainer to colorScheme.onPrimaryContainer
        else -> colorScheme.surfaceVariant to colorScheme.onSurfaceVariant
    }
}

fun Int.toWeatherIconRes(): Int {
    return when (this) {
        0 -> R.drawable.weather_sun
        1, 2, 3 -> R.drawable.weather_sun_clouds
        61, 63, 65, 80, 81, 82 -> R.drawable.weather_rain
        95 -> R.drawable.weather_thunder
        else -> R.drawable.weather_sun_clouds
    }
}