package com.example.innovecstask.data.di

import dagger.Module

@Module(
    includes = [
        DataProviderModule::class,
        ServiceModule::class,
        RemoteSourceModule::class,
    ]
)
class NetworkModule {
}