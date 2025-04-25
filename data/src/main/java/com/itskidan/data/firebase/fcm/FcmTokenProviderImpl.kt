package com.itskidan.data.firebase.fcm

import com.google.firebase.messaging.FirebaseMessaging
import com.itskidan.domain.provider.FcmTokenProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FcmTokenProviderImpl @Inject constructor() : FcmTokenProvider {
    override suspend fun getToken(): String {
        return FirebaseMessaging.getInstance().token.await()
    }
}