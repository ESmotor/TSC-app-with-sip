package com.itskidan.tscapp.app

import android.app.Application
import com.itskidan.tscapp.ui.utils.LifecycleObserver
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    val lifecycleObserver = LifecycleObserver()

    override fun onCreate() {
        super.onCreate()
        instance = this
        // Initialize Timber
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: App
            private set
    }
}