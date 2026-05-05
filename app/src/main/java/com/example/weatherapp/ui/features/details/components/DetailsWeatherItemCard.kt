package com.example.weatherapp.ui.features.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.common.extensions.toLocalizedDate
import com.example.weatherapp.ui.common.extensions.toDayName
import com.example.weatherapp.ui.common.extensions.toLocalizedTime
import com.example.weatherapp.ui.common.extensions.toWeatherConditionText
import com.example.weatherapp.ui.common.extensions.toWeatherIconRes
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun DetailsWeatherItemCard(
    date: String,
    temperature: Double,
    weatherCode: Int
) {
    val context = LocalContext.current
    val dayName = date.toDayName(context)
    val formattedDate = date.toLocalizedDate(context)
    val formattedTime = date.toLocalizedTime(context)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val headerText = buildString {
            append("$dayName, $formattedDate")
            if (formattedTime.isNotEmpty()) {
                append(" - $formattedTime")
            }
        }

        Text(
            text = headerText,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )

        Image(
            painter = painterResource(weatherCode.toWeatherIconRes()),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${temperature.toInt()}°",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Text(
                text = weatherCode.toWeatherConditionText(),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { }
        ) {
            val formattedTime = date.toLocalizedTime(context)

            Text(
                text = if (formattedTime.isNotEmpty()) stringResource(R.string.last_update_format, date.toLocalizedTime(context)) else "",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun DetailsWeatherItemCardPreview() {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF4F7FFA), Color(0xFF335FD1)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    WeatherAppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
                .padding(24.dp)
        ) {
            DetailsWeatherItemCard(
                date = "2026-04-14T20:51",
                temperature = 20.0,
                weatherCode = 1
            )
        }
    }
}