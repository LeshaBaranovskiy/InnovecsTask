package com.example.innovecstask.presentation.di

import dagger.Module

@Module(
    includes = [
        ActivityProvider::class
    ]
)
class PresentationModule