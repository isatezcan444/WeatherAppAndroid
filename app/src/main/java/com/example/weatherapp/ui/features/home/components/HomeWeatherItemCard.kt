package com.example.weatherapp.ui.features.home.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.weatherapp.domain.model.WeatherDomainModel
import com.example.weatherapp.ui.common.extensions.toLocalizedDate
import com.example.weatherapp.ui.common.extensions.toLocalizedTime
import com.example.weatherapp.ui.common.extensions.toWeatherConditionText
import com.example.weatherapp.ui.common.extensions.toWeatherIconRes
import com.example.weatherapp.core.utils.WeatherMockData
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun HomeWeatherItemCard(
    weatherData: WeatherDomainModel,
    locationName: String,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onShowDetailsClick: () -> Unit = { },
    onRefreshClick: () -> Unit = { },
    onFavoriteClick: () -> Unit,
) {
    val context = LocalContext.current

    val weatherGradient = Brush.linearGradient(
        colors = listOf(Color(0xFF4F7FFA), Color(0xFF335FD1)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    val formattedDate = weatherData.date.toLocalizedDate(context)
    val formattedTime = weatherData.date.toLocalizedTime(context)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onShowDetailsClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Box(
            modifier = Modifier
                .background(weatherGradient)
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "$locationName, $formattedDate",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(weatherData.weatherCode.toWeatherIconRes()),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(
                            text = "${weatherData.currentTemperature.toInt()}°",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            text = weatherData.weatherCode.toWeatherConditionText(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(modifier = Modifier.clickable { onRefreshClick() }) {
                        Text(
                            text = if (formattedTime.isNotEmpty())
                                stringResource(R.string.last_update_format, formattedTime)
                            else "",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )

                        Spacer(modifier = Modifier.width(3.dp))

                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = stringResource(R.string.action_refresh),
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Row(modifier = Modifier.clickable { onFavoriteClick() }) {
                        Text(
                            text = if (isFavorite) {
                                stringResource(R.string.action_remove_favorites)
                            } else {
                                stringResource(R.string.action_add_favorites)
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )

                        Spacer(modifier = Modifier.width(3.dp))

                        Icon(
                            imageVector = if (isFavorite)
                                Icons.Default.Favorite
                            else
                                Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeWeatherItemCardPreview() {
    WeatherAppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            HomeWeatherItemCard(
                weatherData = WeatherMockData.mockWeather,
                locationName = "İstanbul, Türkiye",
                isFavorite = true,
                onShowDetailsClick = {},
                onRefreshClick = {},
                onFavoriteClick = {}
            )
        }
    }
}