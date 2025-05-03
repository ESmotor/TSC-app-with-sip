package com.itskidan.domain.model

data class CallInfo(
    val callId: String? = null,
    val remoteName: String? = "Unknown",
    val callDirection: CallDirection = CallDirection.UNKNOWN,
    val isMuted: Boolean = false,
    val callStatus: CallStatus = CallStatus.Success,
)