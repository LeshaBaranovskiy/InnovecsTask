package com.example.innovecstask.common.di

import com.example.innovecstask.data.di.NetworkModule
import com.example.innovecstask.presentation.di.PresentationModule
import dagger.Module

@Module(
    includes = [
        PresentationModule::class,
        NetworkModule::class
    ]
)
class ApplicationModule {
}