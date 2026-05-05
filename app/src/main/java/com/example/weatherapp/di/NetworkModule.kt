package com.example.weatherapp.di

import com.example.weatherapp.data.remote.AirQualityApiService
import com.example.weatherapp.data.remote.GeocodingApiService
import com.example.weatherapp.data.remote.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val WEATHER_BASE_URL = "https://api.open-meteo.com/"
    private const val GEOCODING_BASE_URL = "https://geocoding-api.open-meteo.com/"
    private const val AIR_QUALITY_BASE_URL = "https://air-quality-api.open-meteo.com/"

    // -------------------------
    // Qualifiers
    // -------------------------

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class WeatherRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GeocodingRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AirQualityRetrofit

    // -------------------------
    // Base builder
    // -------------------------

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }

    // -------------------------
    // Retrofit instances
    // -------------------------

    @Provides
    @Singleton
    @WeatherRetrofit
    fun provideWeatherRetrofit(
        builder: Retrofit.Builder
    ): Retrofit {
        return builder
            .baseUrl(WEATHER_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    @GeocodingRetrofit
    fun provideGeocodingRetrofit(
        builder: Retrofit.Builder
    ): Retrofit {
        return builder
            .baseUrl(GEOCODING_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    @AirQualityRetrofit
    fun provideAirQualityRetrofit(
        builder: Retrofit.Builder
    ): Retrofit {
        return builder
            .baseUrl(AIR_QUALITY_BASE_URL)
            .build()
    }

    // -------------------------
    // API Services
    // -------------------------

    @Provides
    @Singleton
    fun provideWeatherApi(
        @WeatherRetrofit retrofit: Retrofit
    ): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGeocodingApi(
        @GeocodingRetrofit retrofit: Retrofit
    ): GeocodingApiService {
        return retrofit.create(GeocodingApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAirQualityApi(
        @AirQualityRetrofit retrofit: Retrofit
    ): AirQualityApiService {
        return retrofit.create(AirQualityApiService::class.java)
    }
}