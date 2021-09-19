package com.niket.sample.feature.di

import com.niket.sample.feature.view.HomeActivity
import com.niket.sample.base.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): HomeActivity

}