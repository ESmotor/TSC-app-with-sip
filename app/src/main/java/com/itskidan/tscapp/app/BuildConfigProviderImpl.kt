package com.itskidan.tscapp.app

import com.itskidan.data.config.BuildConfigProvider
import com.itskidan.tscapp.BuildConfig
import javax.inject.Inject

class BuildConfigProviderImpl @Inject constructor() : BuildConfigProvider {
    override fun getNotifyFcmBaseUrl(): String {
        return BuildConfig.NOTIFY_FCM_BASE_URL
    }
}