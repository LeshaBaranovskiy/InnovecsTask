package com.example.innovecstask.domain.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innovecstask.domain.viewmodel.ConfigsViewModel
import com.example.innovecstask.domain.viewmodel.base.ViewModelFactory
import com.example.innovecstask.domain.viewmodel.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap as IntoMap1

@Module
interface ViewModelModule {
    @Binds
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap1
    @ViewModelKey(ConfigsViewModel::class)
    fun bindConfigsViewModel(viewModel: ConfigsViewModel): ViewModel
}