package com.itskidan.data.di

import com.itskidan.data.notification.PushNotifierImpl
import com.itskidan.domain.PushNotifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PushNotifierModule {

    @Provides
    fun providePushNotifier(impl: PushNotifierImpl): PushNotifier {
        return impl
    }

}