package com.itskidan.data.di

import android.content.Context
import com.itskidan.data.linphone.LinphoneCallStatusObserverImpl
import com.itskidan.data.linphone.LinphoneCoreStatusObserverImpl
import com.itskidan.data.linphone.LinphoneRegStatusObserverImpl
import com.itskidan.data.linphone.LinphoneRepositoryImpl
import com.itskidan.domain.repository.linphone.LinphoneCallStatusObserver
import com.itskidan.domain.repository.linphone.LinphoneCoreStatusObserver
import com.itskidan.domain.repository.linphone.LinphoneRegStatusObserver
import com.itskidan.domain.repository.linphone.LinphoneRepository
import dagger.Binds
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
abstract class LinphoneBindsModule {

    @Binds
    abstract fun bindLinphoneRepository(impl: LinphoneRepositoryImpl): LinphoneRepository

}

@Module
@InstallIn(SingletonComponent::class)
object LinphoneProvidesModule {

    @Provides
    @Singleton
    fun provideLinphoneCore(@ApplicationContext context: Context): Core {
        //  Enabling logs in Logcat, Setting log tag, Setting log level
        Factory.instance().run {
            enableLogcatLogs(true)
            setLoggerDomain("Linphone")
            loggingService?.setLogLevel(LogLevel.Debug)
        }
        // Factory.instance().setDebugMode(true,"Linphone")

        val configPath = "${context.filesDir.absolutePath}/linphone_config"
        val core = Factory.instance().createCore(configPath, configPath, context)

        return core
    }

    @Provides
    @Singleton
    fun provideLinphoneCallStatusObserverImpl(core: Core): LinphoneCallStatusObserverImpl {
        return LinphoneCallStatusObserverImpl(core)
    }

    @Provides
    @Singleton
    fun provideLinphoneCallStatusObserver(impl: LinphoneCallStatusObserverImpl): LinphoneCallStatusObserver {
        return impl
    }


    @Provides
    @Singleton
    fun provideLinphoneRegStatusObserverImpl(core: Core): LinphoneRegStatusObserverImpl {
        return LinphoneRegStatusObserverImpl(core)
    }

    @Provides
    @Singleton
    fun provideLinphoneRegStatusObserver(impl: LinphoneRegStatusObserverImpl): LinphoneRegStatusObserver {
        return impl
    }


    @Provides
    @Singleton
    fun provideLinphoneCoreStatusObserverImpl(core: Core): LinphoneCoreStatusObserverImpl {
        return LinphoneCoreStatusObserverImpl(core)
    }

    @Provides
    @Singleton
    fun provideLinphoneCoreStatusObserver(impl: LinphoneCoreStatusObserverImpl): LinphoneCoreStatusObserver {
        return impl
    }
}