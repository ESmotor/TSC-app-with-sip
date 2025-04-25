package com.itskidan.domain

interface PushNotifier {
    suspend fun notifyIncomingCall(token: String, caller: String)
}