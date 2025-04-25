package com.itskidan.data.firebase.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.itskidan.domain.FcmTokenSync
import com.itskidan.domain.repository.linphone.PushNotificationHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AppPushService : FirebaseMessagingService() {
    @Inject
    lateinit var pushNotificationHandler: PushNotificationHandler
    @Inject
    lateinit var fcmTokenSync: FcmTokenSync

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.Forest.tag("MyLog").d("[FCM] NewToken: $token")
        // Launching the FCM Token update
        CoroutineScope(Dispatchers.IO).launch {
            try {
                fcmTokenSync.syncFcmToken(token)
            } catch (e: Exception) {
                Timber.Forest.e(e, "[FCM] Failed to sync token")
            }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.Forest.tag("MyLog").d("[FCM] Receive new message: ${remoteMessage.data}")
        // You can immediately open an activity here or show a notification
        remoteMessage.data.let { data ->
            if (data["messageType"] == "incoming_call") {
                pushNotificationHandler.onIncomingCallReceived(data)
            }
        }
    }

}