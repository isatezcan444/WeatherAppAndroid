package com.example.weatherapp.ui.features.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.domain.model.WeatherDomainModel
import com.example.weatherapp.ui.common.components.DailyForecastItem
import com.example.weatherapp.ui.common.components.HourlyForecastRow
import com.example.weatherapp.ui.common.components.SectionHeader
import com.example.weatherapp.ui.common.extensions.toDayName
import com.example.weatherapp.ui.common.extensions.toWeatherConditionText
import com.example.weatherapp.ui.features.home.components.HomeSearchBar
import com.example.weatherapp.ui.features.home.components.HomeWeatherItemCard
import com.example.weatherapp.ui.features.home.components.HomeWeatherSummaryCard
import com.example.weatherapp.ui.features.home.components.HomeWeatherTopAppBar
import com.example.weatherapp.core.utils.WeatherMockData
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun HomeContent(
    weatherData: WeatherDomainModel,
    searchSuggestions: List<WeatherLocation>,
    favoriteCities: List<WeatherLocation>,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onQueryChanged: (String) -> Unit,
    onCitySelected: (WeatherLocation) -> Unit,
    onShowDetailsClick: (String) -> Unit,
    onDayDetailsClick: (String) -> Unit,
    onRefreshClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onFavoriteToggle: (WeatherLocation) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    val current = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeWeatherTopAppBar(
                    currentLocationName = weatherData.locationName,
                    searchQuery = searchQuery,
                    isSearchActive = isSearchActive,
                    onQueryChanged = {
                        searchQuery = it
                        onQueryChanged(searchQuery)
                    },
                    onSearchToggle = { active ->
                        isSearchActive = active
                        if (!isSearchActive) {
                            searchQuery = ""
                            onQueryChanged("")
                        }
                    },
                    onSettingsClick = onSettingsClick,
                    onKeyboardDone = {
                        searchQuery = ""
                        onQueryChanged("")
                        isSearchActive = false
                    }
                )

                HomeSearchBar(
                    currentLocationName = weatherData.locationName,
                    searchSuggestions = searchSuggestions,
                    favoriteCities = favoriteCities,
                    onQueryChanged = onQueryChanged,
                    onCitySelected = onCitySelected,
                    onSettingsClick = onSettingsClick
                )
            }
        }

        item {
            HomeWeatherItemCard(
                weatherData = weatherData,
                locationName = weatherData.locationName.substringBefore(","),
                isFavorite = isFavorite,
                modifier = Modifier.padding(horizontal = 16.dp),
                onShowDetailsClick = {
                    onShowDetailsClick(weatherData.date.substringBefore("T"))
                },
                onRefreshClick = onRefreshClick,
                onFavoriteClick = { onFavoriteToggle(weatherData.location) }
            )
        }

        item {
            SectionHeader(
                title = stringResource(R.string.title_hourly_forecast),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            HourlyForecastRow(
                hourlyForecasts = weatherData.hourlyForecast,
                contentPadding = PaddingValues(horizontal = 16.dp)
            )
        }

        item {
            SectionHeader(
                title = stringResource(R.string.title_daily_forecast),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            HomeWeatherSummaryCard(
                weatherCode = weatherData.weatherCode,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        items(weatherData.dailyForecast.drop(1)) { dailyItem ->
            DailyForecastItem(
                day = dailyItem.date.toDayName(current),
                weatherDescription = dailyItem.weatherCode.toWeatherConditionText(),
                temperature = dailyItem.temperature,
                weatherCode = dailyItem.weatherCode,
                modifier = Modifier.padding(horizontal = 16.dp),
                onDayDetailsClick = {
                    onDayDetailsClick(dailyItem.date)
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeContentPreview() {
    WeatherAppTheme {
        HomeContent(
            weatherData = WeatherMockData.mockWeather,
            searchSuggestions = emptyList(),
            favoriteCities = emptyList(),
            isFavorite = true,
            onQueryChanged = {},
            onCitySelected = {},
            onShowDetailsClick = {},
            onDayDetailsClick = {},
            onRefreshClick = {},
            onSettingsClick = {},
            onFavoriteToggle = {}
        )
    }
}