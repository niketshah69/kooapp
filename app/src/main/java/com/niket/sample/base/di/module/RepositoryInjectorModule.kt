package com.niket.sample.base.di.module

import com.niket.sample.base.di.scope.AppScope
import com.niket.sample.feature.di.HomeActivityServiceProvider
import com.niket.sample.feature.repository.HomeRepository
import com.niket.sample.feature.repository.IHomeRepository
import dagger.Binds
import dagger.Module

@Module(includes = [HomeActivityServiceProvider::class])
abstract class RepositoryInjectorModule {

    @Binds
    @AppScope
    abstract fun provideHomeRepository(repository: HomeRepository): IHomeRepository
}