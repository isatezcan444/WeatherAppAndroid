package com.example.weatherapp.ui.features.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun DetailsInformationGrid(
    humidity: Int,
    windSpeed: Double,
    pressure: Double,
    cloudCover: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            DetailsInformationItem(
                iconRes = R.drawable.icon_blaze_line,
                value = "$humidity%",
                text = stringResource(R.string.label_humidity),
                modifier = Modifier.weight(1f)
            )
            DetailsInformationItem(
                iconRes = R.drawable.icon_haze_line,
                value = "${pressure.toInt()} hPa",
                text = stringResource(R.string.label_pressure),
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DetailsInformationItem(
                iconRes = R.drawable.icon_windy_line,
                value = "$windSpeed km/h",
                text = stringResource(R.string.label_wind_speed),
                modifier = Modifier.weight(1f)
            )
            DetailsInformationItem(
                iconRes = R.drawable.icon_mist_line,
                value = "$cloudCover%",
                text = stringResource(R.string.label_cloudiness),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun DetailsInformationGridPreview() {
    WeatherAppTheme {
        DetailsInformationGrid(
            humidity = 44,
            windSpeed = 12.0,
            pressure = 1017.0,
            cloudCover = 10
        )
    }
}