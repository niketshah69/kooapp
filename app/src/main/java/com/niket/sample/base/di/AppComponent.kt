package com.niket.sample.base.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.niket.sample.AppController
import com.niket.sample.base.di.module.AppModules
import com.niket.sample.base.di.module.ViewModelFactoryProviderModule
import com.niket.sample.base.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModules::class, ViewModelFactoryProviderModule::class]
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