package com.itskidan.domain.repository

interface PushNotificationHandler {
    fun onIncomingCallReceived(data: Map<String, String>)
}