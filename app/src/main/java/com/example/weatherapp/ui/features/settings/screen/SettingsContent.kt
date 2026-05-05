package com.example.weatherapp.ui.features.settings.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.common.components.sheets.BottomSheet
import com.example.weatherapp.ui.features.settings.components.SettingsGroup
import com.example.weatherapp.ui.features.settings.components.SettingsHeader
import com.example.weatherapp.ui.features.settings.components.SettingsItem
import com.example.weatherapp.domain.settings.AppLanguageType
import com.example.weatherapp.domain.settings.AppThemeType
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun SettingsContent(
    currentLanguage: AppLanguageType,
    currentTheme: AppThemeType,
    onLanguageSelected: (AppLanguageType) -> Unit,
    onThemeSelected: (AppThemeType) -> Unit,
    innerPadding: PaddingValues
) {
    var showLanguageSheet by remember { mutableStateOf(false) }
    var showThemeSheet by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        item {
            SettingsHeader(
                titleRes = R.string.settings_header_general
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        item {
            SettingsGroup(
                content = {
                    SettingsItem(
                        titleRes = R.string.settings_item_language,
                        currentRes = when(currentLanguage) {
                            AppLanguageType.SYSTEM -> R.string.settings_label_system
                            AppLanguageType.ENGLISH -> R.string.settings_label_english
                            AppLanguageType.TURKISH -> R.string.settings_label_turkish
                        },
                        onClick = { showLanguageSheet = true }
                    )
                }
            )
        }

        item {
            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }

        item {
            SettingsHeader(
                titleRes = R.string.settings_header_appearance
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        item {
            SettingsGroup(
                content = {
                    SettingsItem(
                        titleRes = R.string.settings_item_theme,
                        currentRes = when(currentTheme) {
                            AppThemeType.SYSTEM -> R.string.settings_label_system
                            AppThemeType.LIGHT -> R.string.settings_label_light
                            AppThemeType.DARK -> R.string.settings_label_dark
                        },
                        onClick = { showThemeSheet = true }
                    )
                }
            )
        }

        item {
            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }

        item {
            SettingsHeader(
                titleRes = R.string.settings_header_about
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        item {
            SettingsGroup(
                content = {
                    SettingsItem(
                        titleRes = R.string.settings_item_privacy,
                        isShowDetailIcon = false,
                        onClick = {}
                    )

                    SettingsItem(
                        titleRes = R.string.settings_item_about,
                        currentRes = R.string.app_version,
                        isShowDetailIcon = false,
                        onClick = {}
                    )
                }
            )
        }
    }

    if (showThemeSheet) {
        BottomSheet(
            titleRes = R.string.settings_title_select_appearance,
            options = AppThemeType.entries,
            selected = currentTheme,
            optionLabel = { theme ->
                when(theme) {
                    AppThemeType.SYSTEM -> R.string.settings_label_system
                    AppThemeType.LIGHT -> R.string.settings_label_light
                    AppThemeType.DARK -> R.string.settings_label_dark
                }
            },
            onSelected = { selectedTheme ->
                onThemeSelected(selectedTheme)
                showThemeSheet = false
            },
            onDismiss = { showThemeSheet = false }
        )
    }

    if (showLanguageSheet) {
        BottomSheet(
            titleRes = R.string.settings_title_select_language,
            options = AppLanguageType.entries,
            selected = currentLanguage,
            optionLabel = { language ->
                when(language) {
                    AppLanguageType.SYSTEM -> R.string.settings_label_system
                    AppLanguageType.ENGLISH -> R.string.settings_label_english
                    AppLanguageType.TURKISH -> R.string.settings_label_turkish
                }
            },
            onSelected = { selectedLanguage ->
                onLanguageSelected(selectedLanguage)
                showLanguageSheet = false
            },
            onDismiss = { showLanguageSheet = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsContentPreview() {
    WeatherAppTheme {
        SettingsContent(
            currentLanguage = AppLanguageType.SYSTEM,
            currentTheme = AppThemeType.SYSTEM,
            onLanguageSelected = {},
            onThemeSelected = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}