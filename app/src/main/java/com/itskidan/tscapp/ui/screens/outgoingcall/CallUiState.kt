package com.itskidan.tscapp.ui.screens.outgoingcall

data class CallUiState(
    val isOutgoingCall: Boolean = false,
    val callState: String = "Calling...",
    val contactCaption: String = "Valera Smirnov",
    val contactAvatar: Any? = null,
    val isMute: Boolean = false,
    val isSpeakerphone: Boolean = false,
    val isCallEnded: Boolean = false,
    val isRunningCall: Boolean = false,
)
