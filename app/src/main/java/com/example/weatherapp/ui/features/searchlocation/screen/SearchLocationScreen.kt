package com.example.weatherapp.ui.features.searchlocation.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.ui.features.home.HomeViewModel
import com.example.weatherapp.ui.features.onboarding.OnboardingViewModel

@Composable
fun SearchLocationScreen(
    homeViewModel: HomeViewModel,
    onConfirmClick: () -> Unit
) {
    val searchResult by homeViewModel.searchResults.collectAsStateWithLifecycle()
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()

    Scaffold { innerPadding ->
        SearchLocationContent(
            searchSuggestions = searchResult,
            innerPadding = innerPadding,
            onQueryChanged = { homeViewModel.onSearchQueryChanged(it) },
            onCitySelected = { cityDto ->
                homeViewModel.onCitySelected(cityDto)
            },
            onConfirmClick = {
                onboardingViewModel.saveCompleteOnboardingStatus()
                onConfirmClick()
            }
        )
    }
}