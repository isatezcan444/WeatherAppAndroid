package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.settings.AppLanguageType
import com.example.weatherapp.domain.settings.AppThemeType
import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {
    fun getTheme() : Flow<AppThemeType>
    suspend fun setTheme(theme: AppThemeType)
    fun getLanguage() : Flow<AppLanguageType>
    suspend fun setLanguage(language: AppLanguageType)
    fun isOnboardingCompleted() : Flow<Boolean>
    suspend fun setOnboardingStatus(state: Boolean)
}