package com.niket.sample.feature.repository

import com.niket.sample.base.helper.ResponseWrapper
import com.niket.sample.feature.model.Response

interface IHomeRepository {

    suspend fun getResponse(pageNumber: Int): ResponseWrapper<Response?>
}