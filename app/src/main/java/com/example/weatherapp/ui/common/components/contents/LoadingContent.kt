package com.example.weatherapp.ui.common.components.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.common.components.indicators.IndeterminateCircularIndicator
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun LoadingContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        IndeterminateCircularIndicator(
            modifier = Modifier.size(72.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoadingContentPreview() {
    WeatherAppTheme {
        LoadingContent()
    }
}