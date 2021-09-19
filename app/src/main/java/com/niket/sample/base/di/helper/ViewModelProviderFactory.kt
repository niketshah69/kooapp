package com.niket.sample.base.di.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.niket.sample.base.di.scope.AppScope
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class ViewModelProviderFactory @Inject constructor(private val mapOfViewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = mapOfViewModels[modelClass] ?: mapOfViewModels.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}