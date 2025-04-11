package com.itskidan.tscapp.ui.screens.outgoingcall

sealed interface CallDirection {
    val isOutgoing: Boolean
}

sealed class CallUiState {

    object Idle : CallUiState() // general

    sealed class Incoming : CallUiState(), CallDirection {
        override val isOutgoing: Boolean = false

        object Ringing : Incoming()
        object Connecting : Incoming()
    }

    sealed class Outgoing : CallUiState(), CallDirection {
        override val isOutgoing: Boolean = true

        object Ringing : Outgoing()
        object Connecting : Outgoing()

    }

    object Running : CallUiState() // general
    object Paused : CallUiState() // general
    object Ended : CallUiState()  // general
    object Error : CallUiState()   // general

}