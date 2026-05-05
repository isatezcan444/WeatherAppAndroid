package com.example.weatherapp.ui.common.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(
    type: WeatherTopAppBarType,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = { },
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors()
) {
    when(type) {
        WeatherTopAppBarType.NORMAL -> {
            TopAppBar(
                title = title,
                navigationIcon = navigationIcon,
                actions = actions,
                colors = colors,
                modifier = modifier
            )
        }
        WeatherTopAppBarType.CENTERED -> {
            CenterAlignedTopAppBar(
                title = title,
                navigationIcon = navigationIcon,
                actions = actions,
                colors = colors,
                modifier = modifier
            )
        }
    }
}

enum class WeatherTopAppBarType {
    NORMAL,
    CENTERED
}