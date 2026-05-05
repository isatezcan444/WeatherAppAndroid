package com.example.weatherapp.ui.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.ui.common.extensions.fullDisplayName
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun HomeSearchBar(
    currentLocationName: String,
    searchSuggestions: List<WeatherLocation>,
    favoriteCities: List<WeatherLocation>,
    onQueryChanged: (String) -> Unit,
    onCitySelected: (WeatherLocation) -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    var isCitySelectListActive by remember { mutableStateOf(false) }

    val shouldShowDropdown = isSearchActive && searchQuery.length >= 3 && searchSuggestions.isNotEmpty()

    Box(modifier = modifier.fillMaxWidth()) {
        HomeWeatherTopAppBar(
            currentLocationName = currentLocationName,
            searchQuery = searchQuery,
            isSearchActive = isSearchActive,
            onQueryChanged = {
                searchQuery = it
                onQueryChanged(it)
            },
            onSearchToggle = { active ->
                isSearchActive = active
                if (!active) {
                    searchQuery = ""
                    onQueryChanged("")
                }
            },
            onCitySelectListClick = {
                isCitySelectListActive = !isCitySelectListActive
            },
            onSettingsClick = onSettingsClick,
            onKeyboardDone = {
                isSearchActive = false
                searchQuery = ""
                onQueryChanged("")
            }
        )

        if (shouldShowDropdown) {
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(0, 150),
                properties = PopupProperties(
                    focusable = false,
                    dismissOnClickOutside = true
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                        )
                ) {
                    searchSuggestions.forEach { city ->
                        DropdownMenuItem(
                            text = { Text(city.fullDisplayName) },
                            leadingIcon = {
                                Icon(Icons.Default.AddLocationAlt, null)
                            },
                            onClick = {
                                onCitySelected(city)
                            }
                        )
                    }
                }
            }
        }

        DropdownMenu(
            expanded = isCitySelectListActive,
            onDismissRequest = {},
            properties = PopupProperties(
                focusable = false,
                dismissOnClickOutside = true
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            if (favoriteCities.isEmpty()) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(R.string.no_favorite_cities)
                        )
                    },
                    onClick = { isCitySelectListActive = false }
                )
            } else {
                favoriteCities.forEach { city ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = city.fullDisplayName
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.MyLocation,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            onCitySelected(city)
                            isCitySelectListActive = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeSearchBarPreview() {
    WeatherAppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            HomeSearchBar(
                currentLocationName = "İstanbul, Türkiye",
                searchSuggestions = emptyList(),
                favoriteCities = emptyList(),
                onQueryChanged = {},
                onSettingsClick = {},
                onCitySelected = {},
            )
        }
    }
}