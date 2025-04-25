package com.itskidan.data.retrofit.model

data class PushRequest(
    val token: String,
    val messageType: String,
    val caller: String
)