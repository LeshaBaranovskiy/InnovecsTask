package com.example.innovecstask.data.di

import com.example.innovecstask.common.utils.Constants
import com.example.innovecstask.data.api.ApiService
import com.example.innovecstask.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideApiService(remoteDataSource: RemoteDataSource): ApiService =
        remoteDataSource.buildApi(
            ApiService::class.java,
            Constants.API_SERVICE_URL
        )
}