package com.example.weatherapp.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.common.extensions.toWeatherIconRes
import com.example.weatherapp.ui.theme.LightBlue
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun DailyForecastItem(
    day: String,
    weatherDescription: String,
    temperature: Double,
    weatherCode: Int,
    modifier: Modifier = Modifier,
    onDayDetailsClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onDayDetailsClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = LightBlue
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color(0xFF9AB6FF),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(weatherCode.toWeatherIconRes()),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(
                modifier = Modifier.width(20.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = Color.Black
                )

                Text(
                    text = weatherDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

            Text(
                text = "${temperature.toInt()}°",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Black
            )

            Spacer(
                modifier = Modifier.width(18.dp)
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyForecastItemPreview() {
    WeatherAppTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            DailyForecastItem(
                day = stringResource(R.string.label_today),
                weatherDescription = stringResource(R.string.weather_desc_clear),
                temperature = 20.0,
                weatherCode = 1,
                modifier = Modifier.fillMaxWidth(),
                onDayDetailsClick = {}
            )
        }
    }
}