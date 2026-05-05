package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.data.local.datastore.AppPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context: Context) : AppPreferenceManager {
        return AppPreferenceManager(context)
    }
}