package com.itskidan.domain.repository

interface FcmRepository {
     suspend fun saveFcmToken(token: String)
     suspend fun getFcmToken(): String?
}