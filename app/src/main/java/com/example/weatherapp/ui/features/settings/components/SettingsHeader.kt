package com.example.weatherapp.ui.features.settings.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun SettingsHeader(
    @StringRes titleRes: Int,
) {
    Text(
        text = stringResource(titleRes),
        style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingsHeaderPreview() {
    WeatherAppTheme {
        SettingsHeader(
            titleRes = R.string.settings_header_general
        )
    }
}