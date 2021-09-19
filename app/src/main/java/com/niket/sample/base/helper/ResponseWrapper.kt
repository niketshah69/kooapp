package com.niket.sample.base.helper

import android.util.Log
import androidx.annotation.Keep

@Keep
sealed class ResponseWrapper<T>(val data: T? = null) {
    class Success<T>(data: T) : ResponseWrapper<T>(data)
    class Loading<T> : ResponseWrapper<T>()
    class Error<T>(val cause: Throwable? = null) : ResponseWrapper<T>()
}

object ResponseUtil {

    suspend fun <T> getResponseSafely(getResponse: suspend () -> T): ResponseWrapper<T> {
        return try {
            ResponseWrapper.Success(getResponse())
        } catch (e: Exception) {
            Log.e("API ERROR", e.stackTraceToString())
            ResponseWrapper.Error(e)
        }
    }
}