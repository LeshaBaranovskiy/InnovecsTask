package com.example.innovecstask.data.di

import com.example.innovecstask.data.source.RemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteSourceModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        gson: Gson
    ): RemoteDataSource =
        RemoteDataSource(
            gson
        )

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()
}