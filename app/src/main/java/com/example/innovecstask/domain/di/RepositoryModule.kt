package com.example.innovecstask.domain.di

import com.example.innovecstask.data.api.ApiDataProvider
import com.example.innovecstask.domain.repository.ConfigRepository
import com.example.innovecstask.domain.repository.ConfigRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideConfigRepository(
        apiDataProvider: ApiDataProvider
    ): ConfigRepository =
        ConfigRepositoryImpl(
            apiDataProvider
        )
}