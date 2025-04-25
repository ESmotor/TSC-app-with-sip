package com.itskidan.tscapp.di

import android.content.Context
import com.itskidan.domain.repository.linphone.PushNotificationHandler
import com.itskidan.tscapp.utils.PushNotificationHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PushNotificationHandlerModule {

    @Provides
    @Singleton
    fun providePushNotificationHandler(
        @ApplicationContext context: Context
    ): PushNotificationHandler {
        return PushNotificationHandlerImpl(context)
    }
}