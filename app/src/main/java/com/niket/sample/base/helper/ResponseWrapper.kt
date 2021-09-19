package com.niket.sample.base.helper

import androidx.annotation.Keep

@Keep
sealed class ResponseWrapper<T>(val data: T? = null) {
    class Success<T>(data: T) : ResponseWrapper<T>(data)
    class Loading<T> : ResponseWrapper<T>()
    class Error<T>(val cause: Throwable? = null) : ResponseWrapper<T>()
}