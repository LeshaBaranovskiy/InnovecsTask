package com.example.innovecstask.domain.di

import com.example.innovecstask.domain.repository.ConfigRepository
import com.example.innovecstask.domain.usecase.ConfigsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetConfigsUseCase(
        configRepository: ConfigRepository
    ): ConfigsUseCase =
        ConfigsUseCase(configRepository)
}