package com.example.weatherapp.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.domain.settings.AppLanguageType
import com.example.weatherapp.domain.settings.AppThemeType
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

private val Context.dataStore by preferencesDataStore(name = "user_settings")

class AppPreferenceManager(private val context: Context) {
    private val gson = Gson()
    private val ioDispatcher = Dispatchers.IO

    companion object {
        val THEME = stringPreferencesKey("app_theme")
        val LANGUAGE = stringPreferencesKey("app_language")
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val SELECTED_LOCATION = stringPreferencesKey("selected_location")
        val FAVORITE_LOCATIONS = stringSetPreferencesKey("favorite_locations")
    }

    // ---------------- LANGUAGE ----------------

    val language: Flow<AppLanguageType> = context.dataStore.data.map { preferences ->
        val name = preferences[LANGUAGE] ?: AppLanguageType.SYSTEM.name

        runCatching {
            AppLanguageType.valueOf(name)
        }.getOrDefault(AppLanguageType.SYSTEM)
    }

    suspend fun saveLanguage(language: AppLanguageType) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE] = language.name
        }
    }

    // ---------------- THEME ----------------

    val theme: Flow<AppThemeType> = context.dataStore.data.map { preferences ->
        val name = preferences[THEME] ?: AppThemeType.SYSTEM.name

        runCatching {
            AppThemeType.valueOf(name)
        }.getOrDefault(AppThemeType.SYSTEM)
    }

    suspend fun saveTheme(theme: AppThemeType) {
        context.dataStore.edit { preferences ->
            preferences[THEME] = theme.name
        }
    }

    // ---------------- ONBOARDING ----------------

    val isOnboardingCompleted: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[ONBOARDING_COMPLETED] ?: false
        }

    suspend fun saveOnboardingStatus(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = completed
        }
    }

    // ---------------- SELECTED LOCATION ----------------

    suspend fun saveSelectedLocation(location: WeatherLocation) {
        val json = withContext(ioDispatcher) {
            gson.toJson(location)
        }

        context.dataStore.edit { preferences ->
            preferences[SELECTED_LOCATION] = json
        }
    }

    val selectedLocation: Flow<WeatherLocation?> =
        context.dataStore.data.map { preferences ->
            preferences[SELECTED_LOCATION]?.let { json ->
                runCatching {
                    gson.fromJson(json, WeatherLocation::class.java)
                }.getOrNull()
            }
        }

    // ---------------- FAVORITES ----------------

    val favoriteLocations: Flow<List<WeatherLocation>> =
        context.dataStore.data.map { preferences ->
            val jsonSet = preferences[FAVORITE_LOCATIONS] ?: emptySet()

            jsonSet.mapNotNull { json ->
                runCatching {
                    gson.fromJson(json, WeatherLocation::class.java)
                }.getOrNull()
            }
        }

    suspend fun toggleFavoriteLocation(location: WeatherLocation) {
        val json = withContext(ioDispatcher) {
            gson.toJson(location)
        }

        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITE_LOCATIONS] ?: emptySet()
            val updated = currentFavorites.toMutableSet()

            val exists = updated.contains(json)

            if (exists) {
                updated.remove(json)
            } else {
                updated.add(json)
            }

            preferences[FAVORITE_LOCATIONS] = updated
        }
    }
}