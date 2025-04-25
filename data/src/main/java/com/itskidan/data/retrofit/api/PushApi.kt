package com.itskidan.data.retrofit.api

import com.itskidan.data.retrofit.model.PushRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PushApi {
    @POST("sendPushNotification")
    suspend fun sendPush(@Body request: PushRequest): Response<Unit>
}