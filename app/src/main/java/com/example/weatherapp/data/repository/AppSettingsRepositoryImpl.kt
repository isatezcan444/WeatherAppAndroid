package com.example.weatherapp.data.repository

import com.example.weatherapp.data.local.datastore.AppPreferenceManager
import com.example.weatherapp.domain.repository.AppSettingsRepository
import com.example.weatherapp.domain.settings.AppLanguageType
import com.example.weatherapp.domain.settings.AppThemeType
import javax.inject.Inject

class AppSettingsRepositoryImpl @Inject constructor(
    private val appPreferenceManager: AppPreferenceManager
) : AppSettingsRepository {

    override fun getLanguage() = appPreferenceManager.language
    override suspend fun setLanguage(language: AppLanguageType) = appPreferenceManager.saveLanguage(language)
    override fun getTheme() = appPreferenceManager.theme
    override suspend fun setTheme(theme: AppThemeType) = appPreferenceManager.saveTheme(theme)
    override fun isOnboardingCompleted() = appPreferenceManager.isOnboardingCompleted
    override suspend fun setOnboardingStatus(state: Boolean) = appPreferenceManager.saveOnboardingStatus(state)
}