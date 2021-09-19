package com.niket.sample.feature.repository

import com.niket.sample.base.di.scope.AppScope
import com.niket.sample.base.helper.ResponseWrapper
import javax.inject.Inject

@AppScope
class HomeRepository @Inject constructor(
    private val networkSource: UserNetworkSource
) : IHomeRepository {
    override suspend fun getResponse(pageNumber: Int): ResponseWrapper<Any?> {
        return networkSource.getResponse(pageNumber)
    }
}