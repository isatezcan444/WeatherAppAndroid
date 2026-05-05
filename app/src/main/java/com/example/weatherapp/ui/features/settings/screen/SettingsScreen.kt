package com.example.weatherapp.ui.features.settings.screen

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.ui.common.components.WeatherTopAppBar
import com.example.weatherapp.ui.common.components.WeatherTopAppBarType
import com.example.weatherapp.ui.features.settings.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val currentLanguage = viewModel.currentLanguage.collectAsState().value
    val currentTheme = viewModel.currentTheme.collectAsState().value

    Scaffold(
        topBar = {
            WeatherTopAppBar(
                type = WeatherTopAppBarType.NORMAL,
                title = {
                    Text(
                        text = stringResource(R.string.title_settings),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = MaterialTheme.colorScheme.primary,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        SettingsContent(
            currentLanguage = currentLanguage,
            currentTheme = currentTheme,
            onLanguageSelected = viewModel::saveSelectedLanguage,
            onThemeSelected = viewModel::saveSelectedTheme,
            innerPadding = innerPadding
        )
    }
}