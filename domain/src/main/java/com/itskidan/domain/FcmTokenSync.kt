package com.itskidan.domain

interface FcmTokenSync {
    suspend fun syncFcmToken(token: String? = null)
}