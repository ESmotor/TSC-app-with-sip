package com.itskidan.tscapp.platform.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.itskidan.domain.vibration.VibrationController
import com.itskidan.tscapp.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import org.linphone.core.Call
import org.linphone.core.Core
import org.linphone.core.CoreListenerStub
import org.linphone.core.Factory
import org.linphone.core.tools.AndroidPlatformHelper
import org.linphone.mediastream.Version
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class CoreService : Service() {

    @Inject
    lateinit var vibrationController: VibrationController

    companion object {
        private const val SERVICE_NOTIF_ID = 1
        private const val SERVICE_NOTIFICATION_CHANNEL_ID = "org_linphone_core_service_notification_channel"
        private const val SERVICE_NOTIFICATION_CHANNEL_NAME = "Linphone Core Service"
        private const val SERVICE_NOTIFICATION_CHANNEL_DESC = "Used to keep the call(s) alive"
        private const val SERVICE_NOTIFICATION_TITLE = "Linphone Core Service"
        private const val SERVICE_NOTIFICATION_CONTENT = "Used to keep the call(s) alive"
    }

    private var isInForegroundMode = false
    private var serviceNotification: Notification? = null
    private var isListenerAdded = false

    private lateinit var listener: CoreListenerStub

    override fun onCreate() {
        super.onCreate()

        Factory.instance() // Ensure Linphone Factory is initialized

        if (Version.sdkAboveOrEqual(Version.API26_O_80)) {
            createServiceNotificationChannel()
        }

        listener = object : CoreListenerStub() {
            override fun onFirstCallStarted(core: Core) {
                Timber.tag("MyLog").d( "[CoreService] : First call started")
                val call = core.currentCall ?: core.calls.firstOrNull()

                call?.let {
                    if (!isInForegroundMode) {
                        val isVideo = it.currentParams.isVideoEnabled
                        startForeground(isVideo)
                    }

                    if (it.dir == Call.Dir.Incoming && core.isVibrationOnIncomingCallEnabled) {
                        if (it.state == Call.State.IncomingReceived || it.state == Call.State.IncomingEarlyMedia) {
                            vibrate()
                        }
                    }
                }
            }

            override fun onCallStateChanged(core: Core, call: Call, state: Call.State, message: String) {
                if (state == Call.State.End || state == Call.State.Error || state == Call.State.Connected) {
                    stopVibration()
                }
            }

            override fun onLastCallEnded(core: Core) {
                Timber.tag("MyLog").d("[CoreService] : Last call ended")
                if (isInForegroundMode) stopForegroundService()
            }
        }

        addCoreListener()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.tag("MyLog").d("[CoreService] : Service started")
        if (!isListenerAdded) addCoreListener()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopVibration()
        removeCoreListener()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun addCoreListener() {
        val core = AndroidPlatformHelper.instance().core
        core?.let {
            if (!isListenerAdded) {
                it.addListener(listener)
                isListenerAdded = true

                if (core.callsNb > 0) {
                    val call = core.currentCall ?: core.calls.firstOrNull()
                    call?.let {
                        val isVideo = it.currentParams.isVideoEnabled
                        startForeground(isVideo)

                        if (it.dir == Call.Dir.Incoming && core.isVibrationOnIncomingCallEnabled) {
                            if (it.state == Call.State.IncomingReceived || it.state == Call.State.IncomingEarlyMedia) {
                                vibrate()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun removeCoreListener() {
        AndroidPlatformHelper.instance().core?.let {
            it.removeListener(listener)
            isListenerAdded = false
        }
    }

    private fun startForeground(isVideo: Boolean) {
        if (serviceNotification == null) createServiceNotification()
        startForeground(SERVICE_NOTIF_ID, serviceNotification)
        isInForegroundMode = true
    }

    private fun stopForegroundService() {
        stopForeground(true)
        isInForegroundMode = false
    }

    private fun createServiceNotification() {
        val fullScreenIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val fullScreenPendingIntent = PendingIntent.getActivity(
            this, 0, fullScreenIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        serviceNotification = NotificationCompat.Builder(this, SERVICE_NOTIFICATION_CHANNEL_ID)
            .setContentTitle(SERVICE_NOTIFICATION_TITLE)
            .setContentText(SERVICE_NOTIFICATION_CONTENT)
            .setSmallIcon(applicationInfo.icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setOngoing(true)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .build()
    }

    private fun createServiceNotificationChannel() {
        val channel = NotificationChannel(
            SERVICE_NOTIFICATION_CHANNEL_ID,
            SERVICE_NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = SERVICE_NOTIFICATION_CHANNEL_DESC
            enableVibration(true)
            enableLights(true)
            setShowBadge(false)
        }

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    private fun vibrate() {
        vibrationController.start()
    }

    private fun stopVibration() {
        vibrationController.stop()
    }
}