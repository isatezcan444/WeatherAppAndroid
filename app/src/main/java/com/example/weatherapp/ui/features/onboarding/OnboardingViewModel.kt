package com.example.weatherapp.ui.features.onboarding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.core.location.LocationSettingsManager
import com.example.weatherapp.domain.location.LocationSettingsResult
import com.example.weatherapp.domain.repository.AppSettingsRepository
import com.example.weatherapp.ui.features.onboarding.model.OnboardingModel
import com.example.weatherapp.ui.navigation.Routes
import com.example.weatherapp.domain.settings.AppThemeType
import com.google.android.gms.common.api.ResolvableApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val locationSettingsManager: LocationSettingsManager,
    private val appSettingsRepository: AppSettingsRepository
) : ViewModel() {
    private val _pages = MutableStateFlow(listOf(
        OnboardingModel(
            title = R.string.onboarding_title_1,
            description = R.string.onboarding_desc_1,
            imageRes = R.drawable.onboarding_welcome,
            primaryButtonText = R.string.onboarding_btn_get_started,
            primaryAction = OnboardingAction.NextPage,
            isTitleGradient = true
        ),
        OnboardingModel(
            title = R.string.onboarding_title_2,
            description = R.string.onboarding_desc_2,
            imageRes = R.drawable.onboarding_location,
            primaryButtonText = R.string.onboarding_btn_dark_mode,
            primaryAction = OnboardingAction.SelectTheme(AppThemeType.DARK),
            secondaryButtonText = R.string.onboarding_btn_light_mode,
            secondaryAction = OnboardingAction.SelectTheme(AppThemeType.LIGHT),
            footerText = R.string.onboarding_footer_appearance,
            tertiaryAction = OnboardingAction.SelectTheme(AppThemeType.SYSTEM),
            isTitleGradient = false,
            hasTopTitle = false
        ),
        OnboardingModel(
            title = R.string.onboarding_title_3,
            description = R.string.onboarding_desc_3,
            imageRes = R.drawable.onboarding_location,
            primaryButtonText = R.string.onboarding_btn_use_location,
            primaryAction = OnboardingAction.RequestLocation,
            secondaryButtonText = R.string.onboarding_btn_select_city,
            secondaryAction = OnboardingAction.SelectCityManually,
            footerText = R.string.onboarding_footer_safe_data,
            isTitleGradient = false,
            hasTopTitle = false
        )
    ))
    val pages: StateFlow<List<OnboardingModel>> = _pages.asStateFlow()

    private val _startDestination = mutableStateOf<Any?>(null)
    val startDestination: Any? get() = _startDestination.value

    init {
        observeOnboardingStatus()
    }

    private fun observeOnboardingStatus() {
        viewModelScope.launch {
            appSettingsRepository.isOnboardingCompleted().collect { completed ->
                if (completed) {
                    _startDestination.value = Routes.HomeScreen
                } else {
                    _startDestination.value = Routes.OnboardingScreen
                }
            }
        }
    }

    fun checkDeviceLocationSettings(
        onAllowed: () -> Unit,
        onResolutionRequired: (ResolvableApiException) -> Unit
    ) {
        viewModelScope.launch {
            when (val result = locationSettingsManager.checkLocationSettings()) {

                is LocationSettingsResult.Allowed -> {
                    onAllowed()
                }
                is LocationSettingsResult.ResolutionRequired -> {
                    onResolutionRequired(result.exception)
                }
                is LocationSettingsResult.Failed -> {}
            }
        }
    }

    fun saveSelectedTheme(theme: AppThemeType) {
        viewModelScope.launch {
            appSettingsRepository.setTheme(theme)
        }
    }

    fun saveCompleteOnboardingStatus() {
        viewModelScope.launch {
            appSettingsRepository.setOnboardingStatus(true)
        }
    }
}