package com.example.weatherapp.ui.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.HourlyForecast
import com.example.weatherapp.ui.common.extensions.toLocalizedTime
import com.example.weatherapp.core.utils.WeatherMockData
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun HourlyForecastRow(
    hourlyForecasts: List<HourlyForecast>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val context = LocalContext.current

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = contentPadding
    ) {
        itemsIndexed(hourlyForecasts) { index, forecast ->
            HourlyForecastItem(
                temperature = forecast.temperature,
                time = if (index == 0) stringResource(R.string.label_now) else forecast.time.toLocalizedTime(context),
                weatherCode = forecast.weatherCode
            )
        }
    }
}

@Preview
@Composable
fun HourlyForecastRowPreview() {
    WeatherAppTheme {
        HourlyForecastRow(
            hourlyForecasts = WeatherMockData.mockWeather.hourlyForecast,
            contentPadding = PaddingValues(horizontal = 16.dp)
        )
    }
}