package com.niket.sample.base.di

import android.app.Application
import com.niket.sample.AppController
import com.niket.sample.base.di.module.ActivityBuilders
import com.niket.sample.base.di.module.AppModules
import com.niket.sample.base.di.module.RepositoryInjectorModule
import com.niket.sample.base.di.module.ViewModelFactoryProviderModule
import com.niket.sample.base.di.scope.AppScope
import com.niket.sample.feature.di.HomeActivityServiceProvider
import com.niket.sample.feature.di.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModules::class, ViewModelFactoryProviderModule::class,
        ViewModelProvider::class, ActivityBuilders::class,
        RepositoryInjectorModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: AppController)
}