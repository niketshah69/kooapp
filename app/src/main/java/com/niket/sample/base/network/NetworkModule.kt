package com.niket.sample.base.network

import com.niket.sample.base.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

@Module(includes = [LoggingInterceptorModule::class])
class NetworkModule {
    @Provides
    @AppScope
    fun provideOkHttpClientBuilder(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
    }

    @Provides
    @AppScope
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Provides
    @AppScope
    fun provideOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okHttpClientBuilder.build()
    }

    @Provides
    @AppScope
    fun provideRetrofit(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        retrofitBuilder.apply {
            baseUrl("https://gorest.co.in/")
            client(okHttpClient)
            addConverterFactory(converterFactory)
        }
        return retrofitBuilder.build()
    }
}