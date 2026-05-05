package com.example.weatherapp.ui.common.components.sheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.domain.settings.AppThemeType
import com.example.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BottomSheet(
    titleRes: Int,
    options: List<T>,
    selected: T,
    optionLabel: (T) -> Int,
    onSelected: (T) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(top = 8.dp),
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }

        options.forEach { option ->
            ListItem(
                headlineContent = {
                    Text(
                        text = stringResource(optionLabel(option)),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                trailingContent = {
                    if (option == selected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.clickable {
                    onSelected(option)
                    onDismiss()
                }
            )
        }

        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BottomSheetPreview() {
    WeatherAppTheme {
        BottomSheet(
            titleRes = R.string.settings_title_select_appearance,
            options = AppThemeType.entries,
            selected = AppThemeType.SYSTEM,
            optionLabel = { theme ->
                when (theme) {
                    AppThemeType.SYSTEM -> R.string.settings_label_system
                    AppThemeType.LIGHT -> R.string.settings_label_light
                    AppThemeType.DARK -> R.string.settings_label_dark
                }
            },
            onSelected = {},
            onDismiss = {}
        )
    }
}