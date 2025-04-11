package com.itskidan.tscapp.di

import com.itskidan.data.repository.DrawerRepositoryImpl
import com.itskidan.domain.repository.DrawerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindDrawerRepository(
        impl: DrawerRepositoryImpl
    ): DrawerRepository

}