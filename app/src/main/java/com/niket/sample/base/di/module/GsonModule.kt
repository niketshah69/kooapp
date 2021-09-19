package com.niket.sample.base.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.niket.sample.base.di.scope.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

@Module
class GsonModule {

    @Provides
    @AppScope
    fun provideGson(): Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
        .create()

    @Provides
    @AppScope
    fun provideConverter(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

}