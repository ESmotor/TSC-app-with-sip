package com.itskidan.data.notification

import com.itskidan.data.retrofit.api.PushApi
import com.itskidan.data.retrofit.model.PushRequest
import com.itskidan.domain.PushNotifier
import timber.log.Timber
import javax.inject.Inject

class PushNotifierImpl @Inject constructor(
    private val pushApi: PushApi
) : PushNotifier {

    companion object {
        private const val MESSAGE_TYPE_INCOMING_CALL = "incoming_call"
    }

    override suspend fun notifyIncomingCall(token: String, caller: String) {
        val request = PushRequest(
            token = token,
            messageType = MESSAGE_TYPE_INCOMING_CALL,
            caller = caller
        )

        try {
            val response = pushApi.sendPush(request)
            if (response.isSuccessful) {
                Timber.Forest.tag("MyLog").d("[FCM] Push sent successfully")
            } else {
                Timber.Forest.tag("MyLog").e("[FCM] Failed to send push: ${response.code()}")
            }
        } catch (e: Exception) {
            Timber.Forest.tag("MyLog").e(e,"[FCM] Error sending push")
        }

    }
}