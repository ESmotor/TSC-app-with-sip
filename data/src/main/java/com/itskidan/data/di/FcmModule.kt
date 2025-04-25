package com.itskidan.data.di

import com.itskidan.data.firebase.fcm.FcmTokenProviderImpl
import com.itskidan.data.firebase.fcm.FcmTokenSyncImpl
import com.itskidan.data.repository.impl.FcmRepositoryImpl
import com.itskidan.domain.FcmTokenSync
import com.itskidan.domain.provider.FcmTokenProvider
import com.itskidan.domain.repository.FcmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FcmModule {
    @Provides
    fun provideFcmRepository(impl: FcmRepositoryImpl): FcmRepository {
        return impl
    }

    @Provides
    fun provideFcmTokenProvider(impl: FcmTokenProviderImpl): FcmTokenProvider {
        return impl
    }

    @Provides
    fun provideFcmTokenSync(impl: FcmTokenSyncImpl): FcmTokenSync {
        return impl
    }
}