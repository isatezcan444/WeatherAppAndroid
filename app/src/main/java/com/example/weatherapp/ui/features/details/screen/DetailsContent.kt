package com.example.weatherapp.ui.features.details.screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.ui.common.components.HourlyForecastRow
import com.example.weatherapp.ui.common.components.SectionHeader
import com.example.weatherapp.ui.common.extensions.toAirQualityUiState
import com.example.weatherapp.ui.features.details.components.DetailsInformationCard
import com.example.weatherapp.ui.features.details.components.DetailsInformationGrid
import com.example.weatherapp.ui.features.details.components.DetailsTopAppBar
import com.example.weatherapp.ui.features.details.components.DetailsWeatherItemCard
import com.example.weatherapp.core.utils.WeatherMockData
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun DetailsContent(
    locationName: String,
    dailyForecast: DailyForecast,
    onBackClick: () -> Unit,
    isDarkTheme: Boolean
) {
    val aqiUiState = remember(dailyForecast.detailMetrics.aqiValue) { toAirQualityUiState(dailyForecast.detailMetrics.aqiValue) }
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF4F7FFA), Color(0xFF335FD1)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    val view = LocalView.current

    DisposableEffect(view) {
        val activity = view.context as? Activity
        val insetsController = activity?.window?.let {
            WindowCompat.getInsetsController(it, view)
        }

        if (!isDarkTheme){
            insetsController?.isAppearanceLightStatusBars = false
        }

        onDispose {
            if (!isDarkTheme) {
                insetsController?.isAppearanceLightStatusBars = true
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gradient)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DetailsTopAppBar(
                        currentLocationName = locationName,
                        onBackClick = onBackClick
                    )

                    DetailsWeatherItemCard(
                        date = dailyForecast.date,
                        temperature = dailyForecast.temperature,
                        weatherCode = dailyForecast.weatherCode
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }

        item {
            SectionHeader(
                title = stringResource(R.string.title_hourly_forecast),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            HourlyForecastRow(
                hourlyForecasts = dailyForecast.hourlyForecast,
                contentPadding = PaddingValues(horizontal = 16.dp)
            )
        }

        item {
            SectionHeader(
                title = stringResource(R.string.title_detail_information),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            DetailsInformationCard(
                title = stringResource(aqiUiState.titleRes),
                description = stringResource(aqiUiState.descriptionRes),
                aqiValue = dailyForecast.detailMetrics.aqiValue,
                indicatorColor = aqiUiState.color,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            DetailsInformationGrid(
                humidity = dailyForecast.detailMetrics.humidity,
                windSpeed = dailyForecast.detailMetrics.windSpeed,
                pressure = dailyForecast.detailMetrics.pressure,
                cloudCover = dailyForecast.detailMetrics.cloudiness,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsContentPreview() {
    WeatherAppTheme {
        val todayForecast = WeatherMockData.mockWeather.dailyForecast.first()
        DetailsContent(
            locationName = WeatherMockData.mockWeather.locationName,
            dailyForecast = todayForecast,
            onBackClick = {},
            isDarkTheme = false
        )
    }
}