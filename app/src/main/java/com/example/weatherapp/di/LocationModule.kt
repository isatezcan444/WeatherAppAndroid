package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.core.location.AndroidLocationNameResolver
import com.example.weatherapp.core.location.FusedLocationTracker
import com.example.weatherapp.domain.location.LocationNameResolver
import com.example.weatherapp.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    @Singleton
    abstract fun bindLocationTracker(
        fusedLocationTracker: FusedLocationTracker
    ): LocationTracker

    @Binds
    @Singleton
    abstract fun bindLocationNameResolver(
        androidLocationNameResolver: AndroidLocationNameResolver
    ): LocationNameResolver

    companion object {
        @Provides
        @Singleton
        fun provideFusedLocationProviderClient(
            application: Application
        ): FusedLocationProviderClient {
            return LocationServices.getFusedLocationProviderClient(application)
        }
    }
}