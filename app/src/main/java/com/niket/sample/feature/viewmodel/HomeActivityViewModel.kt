package com.niket.sample.feature.viewmodel

import androidx.lifecycle.ViewModel
import com.niket.sample.feature.repository.IHomeRepository
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    private val repository: IHomeRepository
) : ViewModel()