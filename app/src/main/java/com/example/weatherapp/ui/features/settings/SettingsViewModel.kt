package com.example.weatherapp.ui.features.settings

import com.example.weatherapp.core.locale.LocaleHelper
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.AppSettingsRepository
import com.example.weatherapp.domain.settings.AppLanguageType
import com.example.weatherapp.domain.settings.AppThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository,
    private val application: Application
) : ViewModel() {
    val currentLanguage: StateFlow<AppLanguageType> = appSettingsRepository.getLanguage().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AppLanguageType.SYSTEM
    )

    val currentTheme: StateFlow<AppThemeType> = appSettingsRepository.getTheme().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AppThemeType.SYSTEM
    )

    fun saveSelectedLanguage(language: AppLanguageType) {
        viewModelScope.launch {
            appSettingsRepository.setLanguage(language)
            LocaleHelper.setLocale(application,language.code)
        }
    }

    fun saveSelectedTheme(theme: AppThemeType) {
        viewModelScope.launch {
            appSettingsRepository.setTheme(theme)
        }
    }
}