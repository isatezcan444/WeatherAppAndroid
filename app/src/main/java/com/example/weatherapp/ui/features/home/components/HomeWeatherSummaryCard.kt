package com.example.weatherapp.ui.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.common.extensions.toWeatherSummaryThemeColors
import com.example.weatherapp.ui.common.extensions.toWeatherSummaryUi
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun HomeWeatherSummaryCard(
    weatherCode: Int,
    modifier: Modifier = Modifier,
) {
    val (title, subTitle) = weatherCode.toWeatherSummaryUi()
    val (bgColor, tintColor) = weatherCode.toWeatherSummaryThemeColors()

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = tintColor,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = tintColor
                )

                Text(
                    text = subTitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 14.sp
                    ),
                    color = tintColor.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeWeatherSummaryCardPreview() {
    WeatherAppTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeWeatherSummaryCard(
                weatherCode = 0,
                modifier = Modifier.fillMaxWidth()
            )

            HomeWeatherSummaryCard(
                weatherCode = 3,
                modifier = Modifier.fillMaxWidth()
            )

            HomeWeatherSummaryCard(
                weatherCode = 61,
                modifier = Modifier.fillMaxWidth()
            )

            HomeWeatherSummaryCard(
                weatherCode = 71,
                modifier = Modifier.fillMaxWidth()
            )

            HomeWeatherSummaryCard(
                weatherCode = 999,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}