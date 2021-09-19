package com.niket.sample.base.di.module

import com.niket.sample.feature.di.HomeActivityBuilder
import dagger.Module

@Module(includes = [HomeActivityBuilder::class])
class ActivityBuilders