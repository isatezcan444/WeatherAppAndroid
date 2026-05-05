package com.example.weatherapp.core.utils

import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.domain.model.HourlyForecast
import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.domain.model.WeatherDetailMetrics
import com.example.weatherapp.domain.model.WeatherDomainModel

object WeatherMockData {
    private val mockLocation = WeatherLocation(
        name = "İstanbul",
        region = "Türkiye",
        latitude = 41.0082,
        longitude = 28.9784
    )

    private val mockHourly = listOf(
        HourlyForecast("00:00", 15.0, 45),
        HourlyForecast("04:00", 14.0, 45),
        HourlyForecast("08:00", 16.0, 3),
        HourlyForecast("12:00", 18.0, 1),
        HourlyForecast("16:00", 17.0, 3),
        HourlyForecast("20:00", 16.0, 3)
    )

    val mockWeather = WeatherDomainModel(
        date = "2026-04-13",
        location = mockLocation,
        locationName = "İstanbul, Türkiye",
        currentTemperature = 18.0,
        weatherCode = 3,
        hourlyForecast = mockHourly,
        dailyForecast = listOf(
            DailyForecast(
                date = "2026-04-13",
                temperature = 18.0,
                weatherCode = 3,
                hourlyForecast = mockHourly,
                detailMetrics = WeatherDetailMetrics(
                    aqiValue = 42,
                    humidity = 65,
                    pressure = 1018.0,
                    windSpeed = 14.0,
                    cloudiness = 80
                )
            ),
            DailyForecast(
                date = "2026-04-14",
                temperature = 20.0,
                weatherCode = 1,
                hourlyForecast = mockHourly,
                detailMetrics = WeatherDetailMetrics(
                    aqiValue = 35,
                    humidity = 50,
                    pressure = 1020.0,
                    windSpeed = 10.0,
                    cloudiness = 20
                )
            ),
            DailyForecast(
                date = "2026-04-15",
                temperature = 17.0,
                weatherCode = 61,
                hourlyForecast = mockHourly,
                detailMetrics = WeatherDetailMetrics(
                    aqiValue = 55,
                    humidity = 85,
                    pressure = 1012.0,
                    windSpeed = 22.0,
                    cloudiness = 100
                )
            ),
            DailyForecast(
                date = "2026-04-16",
                temperature = 22.0,
                weatherCode = 0,
                hourlyForecast = mockHourly,
                detailMetrics = WeatherDetailMetrics(
                    aqiValue = 28,
                    humidity = 40,
                    pressure = 1022.0,
                    windSpeed = 8.0,
                    cloudiness = 0
                )
            ),
            DailyForecast(
                date = "2026-04-17",
                temperature = 19.0,
                weatherCode = 51,
                hourlyForecast = mockHourly,
                detailMetrics = WeatherDetailMetrics(
                    aqiValue = 48,
                    humidity = 70,
                    pressure = 1015.0,
                    windSpeed = 12.0,
                    cloudiness = 60
                )
            )
        )
    )
}