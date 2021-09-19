package com.niket.sample.base.di.module

import androidx.lifecycle.ViewModelProvider
import com.niket.sample.base.di.helper.ViewModelProviderFactory
import com.niket.sample.base.di.scope.AppScope
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryProviderModule {

    @Binds
    @AppScope
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}