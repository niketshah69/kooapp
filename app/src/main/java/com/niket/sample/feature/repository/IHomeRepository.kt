package com.niket.sample.feature.repository

import com.niket.sample.base.helper.ResponseWrapper

interface IHomeRepository {

    suspend fun getResponse(pageNumber: Int): ResponseWrapper<Any?>
}