package com.niket.sample.base.di.module

import com.niket.sample.base.network.NetworkModule
import dagger.Module

@Module(includes = [GsonModule::class, ContextModule::class, NetworkModule::class])
class AppModules