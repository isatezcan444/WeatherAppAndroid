package com.example.weatherapp.ui.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.common.components.WeatherTopAppBar
import com.example.weatherapp.ui.common.components.WeatherTopAppBarType
import com.example.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeWeatherTopAppBar(
    currentLocationName: String,
    searchQuery: String,
    isSearchActive: Boolean,
    onQueryChanged: (String) -> Unit,
    onSearchToggle: (Boolean) -> Unit,
    onCitySelectListClick: () -> Unit = { },
    onSettingsClick: () -> Unit,
    onKeyboardDone: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isSearchActive) {
        if (isSearchActive) {
            focusRequester.requestFocus()
        }
    }

    WeatherTopAppBar(
        type = WeatherTopAppBarType.NORMAL,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = if (!isSearchActive) Icons.Default.LocationOn else Icons.Default.MyLocation,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                if (!isSearchActive) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            onCitySelectListClick()
                        }
                    ) {
                        Text(
                            text = currentLocationName,
                            style = MaterialTheme.typography.titleMedium,
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                } else {
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = onQueryChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { onKeyboardDone() },
                        ),
                        textStyle = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .fillMaxWidth(),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            Box {
                                if (searchQuery.isEmpty()) {
                                    Text(
                                        text = stringResource(R.string.label_search_city),
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = { onSearchToggle(!isSearchActive) }
            ) {
                Icon(
                    imageVector = if (isSearchActive) Icons.Default.Close else Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(24.dp)
                )
            }

            if (!isSearchActive) {
                IconButton(
                    onClick = onSettingsClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Preview(showBackground = true)
@Composable
fun HomeWeatherTopAppBarNormalPreview() {
    WeatherAppTheme {
        HomeWeatherTopAppBar(
            currentLocationName = "İstanbul, Türkiye",
            searchQuery = "",
            isSearchActive = false,
            onQueryChanged = {},
            onSearchToggle = {},
            onCitySelectListClick = {},
            onSettingsClick = {},
            onKeyboardDone = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeWeatherTopAppBarSearchPreview() {
    WeatherAppTheme {
        HomeWeatherTopAppBar(
            currentLocationName = "",
            searchQuery = "İstanbul",
            isSearchActive = true,
            onQueryChanged = {},
            onSearchToggle = {},
            onCitySelectListClick = {},
            onSettingsClick = {},
            onKeyboardDone = {}
        )
    }
}