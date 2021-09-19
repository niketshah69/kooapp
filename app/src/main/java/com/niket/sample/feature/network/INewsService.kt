package com.niket.sample.feature.network

import com.niket.sample.feature.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IService {
    @GET("/public/v1/posts")
    suspend fun get(
        @Query("page") pageNumber: Int
    ): Response?
}