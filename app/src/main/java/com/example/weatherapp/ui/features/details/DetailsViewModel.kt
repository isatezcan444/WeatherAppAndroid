package com.example.weatherapp.ui.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.ui.features.details.state.DetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: WeatherRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val locationName: String = checkNotNull(savedStateHandle["locationName"])
    private val selectedDate: String = checkNotNull(savedStateHandle["date"])

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    init {
        loadDetails()
    }

    fun loadDetails() {
        viewModelScope.launch {
            _uiState.value = DetailsUiState.Loading
            try {
                val dailyData = repository.getDailyForecastByDate(selectedDate)
                _uiState.value = DetailsUiState.Success(locationName, dailyData)
            } catch (e: Exception) {
                _uiState.value = DetailsUiState.Error(e.localizedMessage ?: "Details data not found")
            }
        }
    }
}