package com.example.innovecstask.domain.di

import dagger.Module

@Module(
    includes = [
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class
    ]
)
class DomainModule {
}