package com.itskidan.tscapp.core.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.itskidan.domain.repository.PushNotificationHandler
import com.itskidan.tscapp.R
import com.itskidan.tscapp.ui.features.call.CallActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class PushNotificationHandlerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PushNotificationHandler {
    override fun onIncomingCallReceived(data: Map<String, String>) {
        Timber.Forest.tag("MyLog").d("[PushNotificationHandlerImpl] onIncomingCallReceived : $data")
        showIncomingCallNotification(data)
    }

    private fun showIncomingCallNotification(data: Map<String, String>) {
        val intent = Intent(context, CallActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra("caller", data["caller"]) // передаем параметры если нужно
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(context, "calls_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // иконка
            .setContentTitle("Входящий звонок")
            .setContentText("От: ${data["caller"]}")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setAutoCancel(true)
            .setFullScreenIntent(pendingIntent, true) // вот тут магия
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
            .setVibrate(longArrayOf(0, 500, 1000))

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            "calls_channel",
            "Входящие звонки",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Уведомления о входящих звонках"
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            enableVibration(true)
        }
        notificationManager.createNotificationChannel(channel)

        notificationManager.notify(1, notificationBuilder.build())
    }
}