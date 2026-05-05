package com.example.weatherapp.ui.features.settings.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun SettingsItem(
    @StringRes titleRes: Int,
    @StringRes currentRes: Int? = null,
    isShowDetailIcon: Boolean = true,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        trailingContent = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                currentRes?.let {
                    Text(
                        text = stringResource(it),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                if (isShowDetailIcon) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null
                    )
                }
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier.clickable { onClick() }
    )
}

@Preview
@Composable
private fun SettingsItemPreview() {
    WeatherAppTheme {
        SettingsItem(
            titleRes = R.string.settings_item_theme,
            currentRes = R.string.settings_label_system,
            onClick = {}
        )
    }
}