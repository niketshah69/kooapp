package com.niket.sample.feature.di

import com.niket.sample.feature.network.IService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HomeActivityServiceProvider {
    @Provides
    fun getService(retrofit: Retrofit): IService = retrofit.create(IService::class.java)
}