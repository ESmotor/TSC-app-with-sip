package com.itskidan.data.di

import android.content.Context
import com.itskidan.data.linphone.LinphoneRepositoryImpl
import com.itskidan.data.linphone.LinphoneStatesObserverImpl
import com.itskidan.domain.PushNotifier
import com.itskidan.domain.repository.FcmRepository
import com.itskidan.domain.repository.SipRepository
import com.itskidan.domain.repository.SipStatesObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.linphone.core.Core
import org.linphone.core.Factory
import org.linphone.core.LogLevel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LinphoneProvidesModule {

    @Provides
    @Singleton
    fun provideLinphoneRepository(impl: LinphoneRepositoryImpl): SipRepository {
        return impl
    }

    @Provides
    @Singleton
    fun provideLinphoneCore(@ApplicationContext context: Context): Core {
        //  Enabling logs in Logcat, Setting log tag, Setting log level
        Factory.instance().run {
            enableLogcatLogs(true)
            setLoggerDomain("Linphone")
            loggingService?.setLogLevel(LogLevel.Debug)
        }

        val configPath = "${context.filesDir.absolutePath}/linphone_config"
        val core = Factory.instance().createCore(configPath, configPath, context)

        return core
    }

    @Provides
    @Singleton
    fun provideLinphoneStatesObserverImpl(
        core: Core,
        notifier: PushNotifier,
        fcmRepository: FcmRepository
    ): LinphoneStatesObserverImpl {
        return LinphoneStatesObserverImpl(core, notifier, fcmRepository)
    }

    @Provides
    @Singleton
    fun provideLinphoneStatesObserver(impl: LinphoneStatesObserverImpl): SipStatesObserver {
        return impl
    }
}