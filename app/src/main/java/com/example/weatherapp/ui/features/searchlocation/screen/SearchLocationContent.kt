package com.example.weatherapp.ui.features.searchlocation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.ui.common.extensions.fullDisplayName
import com.example.weatherapp.ui.features.onboarding.components.OnboardingButton
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun SearchLocationContent(
    searchSuggestions: List<WeatherLocation>,
    innerPadding: PaddingValues,
    onCitySelected: (WeatherLocation) -> Unit,
    onQueryChanged: (String) -> Unit,
    onConfirmClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val shouldShowDropdown = searchQuery.length >= 3 && searchSuggestions.isNotEmpty()

    val weatherGradient = Brush.linearGradient(
        colors = listOf(Color(0xFF4F7FFA), Color(0xFF335FD1)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(24.dp),
    ) {
        Text(
            text = stringResource(R.string.select_location_title),
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.select_location_description),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    onQueryChanged(searchQuery)
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.label_search_city)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = MaterialTheme.shapes.large,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            if (shouldShowDropdown) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.large
                        )
                        .heightIn(max = 250.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    searchSuggestions.forEach { city ->
                        DropdownMenuItem(
                            text = {
                                Text(text = city.fullDisplayName)
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                searchQuery = city.fullDisplayName
                                onQueryChanged("")
                                onCitySelected(city)
                                focusManager.clearFocus()
                            }
                        )
                    }
                }
            }
        }

        OnboardingButton(
            text = stringResource(R.string.select_location_btn_confirm),
            elevationDp = 4.dp,
            brush = weatherGradient,
            onClick = onConfirmClick
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchLocationContentPreview() {
    WeatherAppTheme {
        val mockSuggestions = listOf(
            WeatherLocation(
                name = "İstanbul",
                region = "Türkiye",
                latitude = 0.0,
                longitude = 0.0
            ),
        )

        SearchLocationContent(
            searchSuggestions = mockSuggestions,
            innerPadding = PaddingValues(0.dp),
            onCitySelected = {},
            onQueryChanged = {},
            onConfirmClick = {}
        )
    }
}