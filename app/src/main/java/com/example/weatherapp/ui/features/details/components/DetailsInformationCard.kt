package com.example.weatherapp.ui.features.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.common.components.indicators.CircularIndicator
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun DetailsInformationCard(
    title: String,
    description: String,
    aqiValue: Int,
    indicatorColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 16.dp)
        ) {
            CircularIndicator(
                progress = (aqiValue / 100f).coerceAtMost(1f),
                valueText = aqiValue.toString(),
                activeColor = indicatorColor,
                modifier = Modifier.size(80.dp)
            )

            Spacer(
                modifier = Modifier.width(24.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 18.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailsInformationCardExcellentPreview() {
    WeatherAppTheme {
        DetailsInformationCard(
            title = stringResource(R.string.aqi_title_good),
            description = stringResource(R.string.aqi_desc_good),
            aqiValue = 44,
            indicatorColor = Color(0xFF4CAF50),
            modifier = Modifier.padding(16.dp)
        )
    }
}