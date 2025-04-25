package com.itskidan.tscapp.di

import com.itskidan.data.config.BuildConfigProvider
import com.itskidan.tscapp.app.BuildConfigProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBuildConfigProvider(impl: BuildConfigProviderImpl): BuildConfigProvider {
        return impl
    }
}