package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.AirQualityDto
import com.example.weatherapp.data.model.CityDto
import com.example.weatherapp.data.model.WeatherDto
import com.example.weatherapp.domain.model.WeatherLocation
import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.domain.model.HourlyForecast
import com.example.weatherapp.domain.model.WeatherDetailMetrics
import com.example.weatherapp.domain.model.WeatherDomainModel

fun WeatherDto.toDomainModel(
    locationName: String,
    location: WeatherLocation,
    airQuality: AirQualityDto
): WeatherDomainModel {

    val hourlyDataByDay = hourly.time.mapIndexed { index, time ->
        val dateKey = time.substringBefore("T")
        dateKey to HourlyForecast(
            time = time,
            temperature = hourly.temperatures[index],
            weatherCode = hourly.weatherCodes[index]
        )
    }.groupBy({ it.first }, { it.second })

    val aqiLookupMap = airQuality.hourly.time.mapIndexed { index, fullTime ->
        fullTime.substringBefore("T") to airQuality.hourly.aqiList.getOrNull(index)
    }.toMap()

    val dailyForecastList = daily.time.mapIndexed { index, time ->
        val dailyAqi = aqiLookupMap[time] ?: airQuality.current.aqi

        DailyForecast(
            date = time,
            weatherCode = daily.weatherCodes.getOrElse(index) { 0 },
            temperature = daily.maxTemperatures.getOrElse(index) { 0.0 },
            hourlyForecast = hourlyDataByDay[time] ?: emptyList(),
            detailMetrics = WeatherDetailMetrics(
                aqiValue = dailyAqi,
                humidity = daily.humidity.getOrElse(index) { 0.0 }.toInt(),
                pressure = daily.pressure.getOrElse(index) { 0.0 },
                windSpeed = daily.windSpeed.getOrElse(index) { 0.0 },
                cloudiness = daily.cloudCover.getOrElse(index) { 0 }
            )
        )
    }

    return WeatherDomainModel(
        date = current.time,
        location = location,
        locationName = locationName,
        currentTemperature = current.temperature,
        weatherCode = current.weatherCode,
        hourlyForecast = hourlyDataByDay[current.time.substringBefore("T")] ?: emptyList(),
        dailyForecast = dailyForecastList
    )
}

fun CityDto.toDomainModel(): WeatherLocation {
    return WeatherLocation(
        name = this.name,
        region = this.country ?: "",
        latitude = this.latitude,
        longitude = this.longitude
    )
}