package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.ui.navigation.NavGraph
import com.example.weatherapp.domain.settings.AppThemeType
import com.example.weatherapp.ui.features.settings.SettingsViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val viewModel: SettingsViewModel = hiltViewModel()
            val currentTheme by viewModel.currentTheme.collectAsStateWithLifecycle()
            val isDarkTheme = when (currentTheme) {
                AppThemeType.DARK -> true
                AppThemeType.LIGHT -> false
                AppThemeType.SYSTEM -> isSystemInDarkTheme()
            }

            WeatherAppTheme(
                darkTheme = isDarkTheme
            ) {
                NavGraph(isDarkTheme = isDarkTheme)
            }
        }
    }
}