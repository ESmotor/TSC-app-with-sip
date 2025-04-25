package com.itskidan.domain.repository.linphone

interface PushNotificationHandler {
    fun onIncomingCallReceived(data: Map<String, String>)
}