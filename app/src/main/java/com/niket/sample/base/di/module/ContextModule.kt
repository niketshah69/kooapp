package com.niket.sample.base.di.module

import android.app.Application
import android.content.Context
import com.niket.sample.base.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule {
    @Provides
    @AppScope
    fun provideContext(application: Application): Context = application.applicationContext
}