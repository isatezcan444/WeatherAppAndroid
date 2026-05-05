package com.example.weatherapp.ui.features.onboarding

import com.example.weatherapp.domain.settings.AppThemeType

sealed class OnboardingAction {
    object NextPage : OnboardingAction()
    data class SelectTheme(val themeType: AppThemeType) : OnboardingAction()
    object RequestLocation : OnboardingAction()
    object SelectCityManually : OnboardingAction()
    object FinishOnboarding : OnboardingAction()
}