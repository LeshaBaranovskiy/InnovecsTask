package com.example.innovecstask.presentation.di

import com.example.innovecstask.presentation.activity.BaseActivity
import com.example.innovecstask.presentation.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityProvider {
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

}