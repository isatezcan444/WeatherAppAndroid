package com.example.weatherapp.ui.features.home.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.ui.common.components.contents.ErrorContent
import com.example.weatherapp.ui.common.components.contents.LoadingContent
import com.example.weatherapp.ui.features.home.HomeViewModel
import com.example.weatherapp.ui.features.home.state.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onShowDetailsClick: (String, String) -> Unit,
    onDayDetailsClick: (String, String) -> Unit,
    onSettingsClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val favoriteCities by viewModel.favoriteLocations.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        when (val state = uiState) {
            is HomeUiState.Loading -> {
                LoadingContent()
            }
            is HomeUiState.Success -> {
                HomeContent(
                    weatherData = state.data,
                    searchSuggestions = searchResults,
                    favoriteCities = favoriteCities,
                    isFavorite = viewModel.isCurrentLocationFavorite,
                    contentPadding = innerPadding,
                    onQueryChanged = viewModel::onSearchQueryChanged,
                    onCitySelected = { cityDto ->
                        viewModel.onCitySelected(cityDto)
                    },
                    onShowDetailsClick = { selectedDate ->
                        onShowDetailsClick(state.data.locationName, selectedDate)
                    },
                    onDayDetailsClick = { selectedDate ->
                        onDayDetailsClick(state.data.locationName, selectedDate)
                    },
                    onRefreshClick = viewModel::refreshWeather,
                    onSettingsClick = onSettingsClick,
                    onFavoriteToggle = viewModel::toggleFavorite
                )
            }
            is HomeUiState.Error -> {
                ErrorContent(
                    onRetryClick = viewModel::refreshWeather
                )
            }
        }
    }
}