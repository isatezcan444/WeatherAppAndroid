package com.example.weatherapp.ui.features.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.datastore.AppPreferenceManager
import com.example.weatherapp.domain.location.LocationNameResolver
import com.example.weatherapp.domain.location.LocationResult
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.ui.common.extensions.fullDisplayName
import com.example.weatherapp.ui.features.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val locationNameResolver: LocationNameResolver,
    private val repository: WeatherRepository,
    private val preferenceManager: AppPreferenceManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchResults = MutableStateFlow<List<WeatherLocation>>(emptyList())
    val searchResults: StateFlow<List<WeatherLocation>> = _searchResults.asStateFlow()

    private val _favoriteLocations = MutableStateFlow<List<WeatherLocation>>(emptyList())
    val favoriteLocations: StateFlow<List<WeatherLocation>> = _favoriteLocations.asStateFlow()

    var isCurrentLocationFavorite by mutableStateOf(false)
        private set

    private var weatherFetchJob: Job? = null
    private var searchJob: Job? = null

    private var lastSelectedLocation: WeatherLocation? = null

    init {
        observePreferenceChanges()
    }

    private fun observePreferenceChanges() {
        viewModelScope.launch {
            combine(
                preferenceManager.selectedLocation,
                preferenceManager.favoriteLocations
            ) { savedLocation, favorites ->
                _favoriteLocations.value = favorites

                if (savedLocation != null) {
                    if (savedLocation.latitude != lastSelectedLocation?.latitude || savedLocation.longitude != lastSelectedLocation?.longitude) {
                        lastSelectedLocation = savedLocation
                        fetchWeather(
                            locationName = savedLocation.fullDisplayName,
                            latitude = savedLocation.latitude,
                            longitude = savedLocation.longitude
                        )
                    }

                    isCurrentLocationFavorite = favorites.any {
                        it.latitude == savedLocation.latitude && it.longitude == savedLocation.longitude
                    }
                } else {
                    loadWeatherWithLocationTracker()
                }
            }.collect()
        }
    }

    fun loadWeatherWithLocationTracker() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading

            when (val locationResult = locationTracker.getCurrentLocation()) {

                is LocationResult.Success -> {
                    val location = locationResult.location

                    val domainLocation = locationNameResolver.resolve(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )

                    onCitySelected(domainLocation)
                }

                is LocationResult.NoPermission -> {
                    _uiState.value = HomeUiState.Error("Location permission denied")
                }

                is LocationResult.GpsDisabled -> {
                    _uiState.value = HomeUiState.Error("GPS is disabled")
                }

                is LocationResult.Error -> {
                    _uiState.value = HomeUiState.Error("An error occurred while tracking location")
                }
            }
        }
    }

    fun fetchWeather(locationName: String, latitude: Double, longitude: Double) {
        weatherFetchJob?.cancel()
        weatherFetchJob = viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val result = repository.getWeatherData(locationName, latitude, longitude)
                _uiState.value = HomeUiState.Success(result)
            } catch (e: Exception) {
                if (weatherFetchJob?.isCancelled == false) {
                    _uiState.value = HomeUiState.Error(e.localizedMessage ?: "Unexpected error occurred")
                }
            }
        }
    }

    fun refreshWeather() {
        lastSelectedLocation?.let {
            fetchWeather(it.fullDisplayName, it.latitude, it.longitude)
        }
    }

    fun toggleFavorite(location: WeatherLocation) {
        viewModelScope.launch {
            repository.toggleFavorite(location)
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchJob?.cancel()

        if (query.isBlank() || query.length < 3) {
            _searchResults.value = emptyList()
            return
        }

        searchJob = viewModelScope.launch {
            delay(500)
            try {
                val cities = repository.searchCity(query)
                _searchResults.value = cities
            } catch (e: Exception) {
                _searchResults.value = emptyList()
            }
        }
    }

    fun onCitySelected(city: WeatherLocation) {
        viewModelScope.launch {
            preferenceManager.saveSelectedLocation(city)
        }
    }
}