package com.example.innovecstask.common

import com.example.innovecstask.common.di.AppComponent
import com.example.innovecstask.common.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector

class Application : DaggerApplication(), HasAndroidInjector {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val applicationComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        applicationComponent.inject(this)

        appComponent = applicationComponent
        return applicationComponent
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}