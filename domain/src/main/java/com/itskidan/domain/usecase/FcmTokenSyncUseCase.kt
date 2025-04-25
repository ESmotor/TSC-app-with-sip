package com.itskidan.domain.usecase

import com.itskidan.domain.FcmTokenSync
import javax.inject.Inject

class FcmTokenSyncUseCase @Inject constructor(
    private val fcmTokenSync: FcmTokenSync
) {
    suspend operator fun invoke(token: String? = null) {
        fcmTokenSync.syncFcmToken(token)
    }
}