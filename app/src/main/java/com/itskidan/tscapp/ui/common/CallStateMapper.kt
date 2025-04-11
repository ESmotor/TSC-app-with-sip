package com.itskidan.tscapp.ui.common

import com.itskidan.domain.model.linphone.CallDirection
import com.itskidan.domain.model.linphone.LinphoneCallState
import com.itskidan.tscapp.ui.screens.outgoingcall.CallStateConfig
import com.itskidan.tscapp.ui.screens.outgoingcall.OutgoingCallUiState

fun mapSysCallStateToText(state: LinphoneCallState): String {
    return when (state) {
        is LinphoneCallState.Idle -> CallStateConfig.SYS_IDLE
        is LinphoneCallState.IncomingReceived -> CallStateConfig.SYS_INCOMING_RECEIVED
        is LinphoneCallState.PushIncomingReceived -> CallStateConfig.SYS_PUSH_INCOMING_RECEIVED
        is LinphoneCallState.OutgoingInit -> CallStateConfig.SYS_OUTGOING_INIT
        is LinphoneCallState.OutgoingProgress -> CallStateConfig.SYS_OUTGOING_PROGRESS
        is LinphoneCallState.OutgoingRinging -> CallStateConfig.SYS_OUTGOING_RINGING
        is LinphoneCallState.OutgoingEarlyMedia -> CallStateConfig.SYS_OUTGOING_EARLY_MEDIA
        is LinphoneCallState.Connected -> CallStateConfig.SYS_CONNECTED
        is LinphoneCallState.StreamsRunning -> CallStateConfig.SYS_STREAMS_RUNNING
        is LinphoneCallState.Pausing -> CallStateConfig.SYS_PAUSING
        is LinphoneCallState.Paused -> CallStateConfig.SYS_PAUSED
        is LinphoneCallState.Resuming -> CallStateConfig.SYS_RESUMING
        is LinphoneCallState.Referred -> CallStateConfig.SYS_REFERRED
        is LinphoneCallState.Error -> CallStateConfig.SYS_ERROR
        is LinphoneCallState.End -> CallStateConfig.SYS_END
        is LinphoneCallState.PausedByRemote -> CallStateConfig.SYS_PAUSED_BY_REMOTE
        is LinphoneCallState.UpdatedByRemote -> CallStateConfig.SYS_UPDATED_BY_REMOTE
        is LinphoneCallState.IncomingEarlyMedia -> CallStateConfig.SYS_INCOMING_EARLY_MEDIA
        is LinphoneCallState.Updating -> CallStateConfig.SYS_UPDATING
        is LinphoneCallState.Released -> CallStateConfig.SYS_RELEASED
        is LinphoneCallState.EarlyUpdatedByRemote -> CallStateConfig.SYS_EARLY_UPDATED_BY_REMOTE
        is LinphoneCallState.EarlyUpdating -> CallStateConfig.SYS_EARLY_UPDATING
        is LinphoneCallState.Unknown -> CallStateConfig.SYS_UNKNOWN
    }

}


fun mapCallStateToUiState(
    currentState: OutgoingCallUiState,
    callState: LinphoneCallState
): OutgoingCallUiState {
    return when (callState) {
        is LinphoneCallState.Idle -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }

        is LinphoneCallState.IncomingReceived -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_RINGING
            )
        }

        is LinphoneCallState.PushIncomingReceived -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_RINGING
            )
        }

        is LinphoneCallState.OutgoingInit -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_RINGING
            )
        }

        is LinphoneCallState.OutgoingProgress -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_RINGING
            )
        }

        is LinphoneCallState.OutgoingRinging -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_CONNECTING
            )
        }

        is LinphoneCallState.OutgoingEarlyMedia -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }

        is LinphoneCallState.Connected -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_CONNECTING
            )
        }

        is LinphoneCallState.StreamsRunning -> {

            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
//                callStateText = CallStateConfig.UI_RUNNING
                isRunningCall = true
            )
        }

        is LinphoneCallState.Pausing -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }

        is LinphoneCallState.Paused -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_PAUSED
            )
        }

        is LinphoneCallState.Resuming -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }

        is LinphoneCallState.Referred -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }

        is LinphoneCallState.Error -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_ERROR
            )
        }

        is LinphoneCallState.End -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_END
            )
        }

        is LinphoneCallState.PausedByRemote -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                callStateText = CallStateConfig.UI_PAUSED
            )
        }

        is LinphoneCallState.UpdatedByRemote -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }

        is LinphoneCallState.IncomingEarlyMedia -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }

        is LinphoneCallState.Updating -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }


        is LinphoneCallState.Released -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
                isCallEnded = true
            )
        }

        is LinphoneCallState.EarlyUpdatedByRemote -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }

        is LinphoneCallState.EarlyUpdating -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }


        is LinphoneCallState.Unknown -> {
            currentState.copy(
                isOutgoingCall = callState.callDir == CallDirection.OUTGOING,
            )
        }
    }
}