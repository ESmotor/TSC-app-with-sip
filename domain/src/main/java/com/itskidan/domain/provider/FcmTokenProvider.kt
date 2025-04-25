package com.itskidan.domain.provider

interface FcmTokenProvider {
    suspend fun getToken(): String
}