package com.niket.sample.base.network

import android.util.Log
import androidx.viewbinding.BuildConfig
import com.niket.sample.base.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor

@Module
class LoggingInterceptorModule {

    @Provides
    @AppScope
    fun provideLogger(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor {
            if (BuildConfig.DEBUG) {
                Log.d("NETWORK", it)
            } else {
                Log.i("NETWORK", it)
            }
        }
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.BASIC
        }
        return logging
    }
}