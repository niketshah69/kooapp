package com.niket.sample.feature.repository

import com.niket.sample.base.di.scope.AppScope
import com.niket.sample.base.helper.ResponseWrapper
import com.niket.sample.feature.model.Response
import javax.inject.Inject

@AppScope
class HomeRepository @Inject constructor(
    private val networkSource: UserNetworkSource
) : IHomeRepository {
    override suspend fun getResponse(pageNumber: Int): ResponseWrapper<Response?> {
        return networkSource.getResponse(pageNumber)
    }
}