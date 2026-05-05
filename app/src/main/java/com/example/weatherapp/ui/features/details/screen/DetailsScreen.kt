package com.example.weatherapp.ui.features.details.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.ui.common.components.contents.ErrorContent
import com.example.weatherapp.ui.common.components.contents.LoadingContent
import com.example.weatherapp.ui.features.details.DetailsViewModel
import com.example.weatherapp.ui.features.details.state.DetailsUiState

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    isDarkTheme: Boolean
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(val state = uiState) {
        is DetailsUiState.Loading -> {
            LoadingContent()
        }
        is DetailsUiState.Success -> {
            DetailsContent(
                locationName = state.locationName,
                dailyForecast = state.data,
                onBackClick = onBackClick,
                isDarkTheme = isDarkTheme
            )
        }
        is DetailsUiState.Error -> {
            ErrorContent(
                onRetryClick = { viewModel.loadDetails() }
            )
        }
    }
}