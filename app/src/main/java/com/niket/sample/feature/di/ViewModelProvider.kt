package com.niket.sample.feature.di

import androidx.lifecycle.ViewModel
import com.niket.sample.feature.viewmodel.PostsViewModel
import com.niket.sample.base.di.helper.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelProvider {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindHomeActivityViewModel(homeActivityViewModel: PostsViewModel): ViewModel

}