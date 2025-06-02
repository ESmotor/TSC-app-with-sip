package com.itskidan.data.di

import com.itskidan.data.repository.impl.CurrencyRepositoryImpl
import com.itskidan.data.repository.impl.ServiceRepositoryImpl
import com.itskidan.domain.repository.CurrencyRepository
import com.itskidan.domain.repository.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideServiceRepository(impl: ServiceRepositoryImpl): ServiceRepository {
        return impl
    }

    @Provides
    fun provideCurrencyRepository(impl: CurrencyRepositoryImpl): CurrencyRepository {
        return impl
    }
}