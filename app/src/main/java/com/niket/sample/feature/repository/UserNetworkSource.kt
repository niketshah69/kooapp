package com.niket.sample.feature.repository

import com.niket.sample.base.di.scope.AppScope
import com.niket.sample.base.helper.ResponseUtil
import com.niket.sample.feature.network.IService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class UserNetworkSource @Inject constructor(private val service: IService) {

    suspend fun getResponse(pageNumber: Int) =
        withContext(Dispatchers.IO) {
            ResponseUtil.getResponseSafely {
                service.get(pageNumber)
            }
        }
}