package com.itskidan.tscapp.ui.screens.outgoingcall

data class OutgoingCallUiState(
    val callStateText: String = "Calling...",
    val outgoingCallName: String = "Valera Smirnov",
    val userAvatar: Any? = null,
    val isMute: Boolean = false,
    val isSpeakerphone: Boolean = false,
    val isCallEnded: Boolean = false,
    val isOutgoingCall: Boolean = false,
    val isRunningCall: Boolean = false,
)
