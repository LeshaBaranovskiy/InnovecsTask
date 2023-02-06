package com.example.innovecstask.data.di

import com.example.innovecstask.data.api.ApiDataProvider
import com.example.innovecstask.data.api.ApiHelper
import com.example.innovecstask.data.api.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataProviderModule {
    @Provides
    @Singleton
    fun provideApiDataProvider(
        apiService: ApiService
    ): ApiHelper = ApiDataProvider(apiService)


}