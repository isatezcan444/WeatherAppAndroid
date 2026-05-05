package com.example.weatherapp.ui.features.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun SettingsGroup(
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            content()
        }
    }
}

@Preview
@Composable
private fun SettingsGroupPreview() {
    WeatherAppTheme {
        SettingsGroup(
            content = {
                SettingsItem(
                    titleRes = R.string.settings_item_theme,
                    currentRes = R.string.settings_label_system,
                    onClick = {}
                )
            }
        )
    }
}