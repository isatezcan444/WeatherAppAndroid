package com.example.weatherapp.ui.features.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.common.components.WeatherTopAppBar
import com.example.weatherapp.ui.common.components.WeatherTopAppBarType
import com.example.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopAppBar(
    currentLocationName: String,
    onBackClick: () -> Unit = { }
) {
    WeatherTopAppBar(
        type = WeatherTopAppBarType.CENTERED,
        title = {
            Text(
                text = currentLocationName,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun DetailsTopAppBarPreview() {
    WeatherAppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4F7FFA))
                .padding(bottom = 8.dp)
        ) {
            DetailsTopAppBar(
                currentLocationName = "İstanbul, Türkiye",
                onBackClick = {}
            )
        }
    }
}