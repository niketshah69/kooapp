package com.niket.sample.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niket.sample.base.helper.ResponseWrapper
import com.niket.sample.feature.model.Response
import com.niket.sample.feature.repository.IHomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val repository: IHomeRepository
) : ViewModel() {
    private val newResponse by lazy {
        MutableLiveData<ResponseWrapper<Response?>>()
    }

    fun getResponseLiveData(): LiveData<ResponseWrapper<Response?>> =
        newResponse

    fun changePreference(pageNumber: Int) {
        newResponse.value = ResponseWrapper.Loading()
        viewModelScope.launch {
            val responseWrapper: ResponseWrapper<Response?> =
                when (val response = repository.getResponse(pageNumber)) {
                    is ResponseWrapper.Success -> ResponseWrapper.Success(response.data)
                    is ResponseWrapper.Error -> ResponseWrapper.Error(response.cause)
                    else -> ResponseWrapper.Error(IllegalStateException("Unknown State"))
                }
            newResponse.value = responseWrapper
        }
    }
}