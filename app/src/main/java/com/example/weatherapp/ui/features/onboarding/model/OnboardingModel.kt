package com.example.weatherapp.ui.features.onboarding.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.weatherapp.ui.features.onboarding.OnboardingAction

data class OnboardingModel(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val primaryButtonText: Int,
    val primaryAction: OnboardingAction,
    @StringRes val secondaryButtonText: Int? = null,
    val secondaryAction: OnboardingAction? = null,
    val tertiaryAction: OnboardingAction? = null,
    val isTitleGradient: Boolean = false,
    @StringRes val footerText: Int? = null,
    val hasTopTitle: Boolean = true
)