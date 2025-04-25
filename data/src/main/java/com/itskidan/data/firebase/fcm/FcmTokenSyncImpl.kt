package com.itskidan.data.firebase.fcm

import com.itskidan.domain.FcmTokenSync
import com.itskidan.domain.provider.FcmTokenProvider
import com.itskidan.domain.repository.FcmRepository
import timber.log.Timber
import javax.inject.Inject

class FcmTokenSyncImpl @Inject constructor(
    private val fcmRepository: FcmRepository,
    private val fcmTokenProvider: FcmTokenProvider
) : FcmTokenSync {

    override suspend fun syncFcmToken(token: String?) {
        try {
            val currentToken = token ?: fcmTokenProvider.getToken()
            val savedToken = fcmRepository.getFcmToken()

            if (currentToken != savedToken) {
                fcmRepository.saveFcmToken(currentToken)
                Timber.tag("Fcm").d("FCM token updated: $currentToken")
            } else {
                Timber.tag("Fcm").d("FCM token is up to date")
            }

        } catch (e: Exception) {
            Timber.tag("Fcm").e("FCM token sync error: ${e.message}")
        }
    }
}