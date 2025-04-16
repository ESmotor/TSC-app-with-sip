package com.itskidan.tscapp.ui.common

import com.itskidan.domain.model.linphone.CallDirection
import com.itskidan.domain.model.linphone.LinphoneCallState
import com.itskidan.tscapp.ui.screens.outgoingcall.CallStateConfig
import com.itskidan.tscapp.ui.screens.outgoingcall.CallUiState

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
    currentState: CallUiState,
    callState: LinphoneCallState,
    timer: Timer
): CallUiState {
    val baseState = currentState.copy(
        isOutgoingCall = callState.callDirection == CallDirection.OUTGOING,
    )
    return when (callState) {
        is LinphoneCallState.Idle -> {
            baseState
        }

        is LinphoneCallState.IncomingReceived -> {
            baseState.copy(
                callState = CallStateConfig.UI_RINGING
            )
        }

        is LinphoneCallState.PushIncomingReceived -> {
            baseState.copy(
                callState = CallStateConfig.UI_RINGING
            )
        }

        is LinphoneCallState.OutgoingInit -> {
            baseState.copy(
                callState = CallStateConfig.UI_RINGING
            )
        }

        is LinphoneCallState.OutgoingProgress -> {
            baseState.copy(
                callState = CallStateConfig.UI_RINGING
            )
        }

        is LinphoneCallState.OutgoingRinging -> {
            baseState.copy(
                callState = CallStateConfig.UI_CONNECTING
            )
        }

        is LinphoneCallState.OutgoingEarlyMedia -> {
            baseState
        }

        is LinphoneCallState.Connected -> {
            baseState.copy(
                callState = CallStateConfig.UI_CONNECTING
            )
        }

        is LinphoneCallState.StreamsRunning -> {
            if (!currentState.isRunningCall) {
                timer.start()
            } else {
                timer.resume()
            }
            baseState.copy(
                isRunningCall = true
            )
        }

        is LinphoneCallState.Pausing -> {
            baseState
        }

        is LinphoneCallState.Paused -> {
            timer.pause()
            baseState.copy(
                callState = CallStateConfig.UI_PAUSED
            )
        }

        is LinphoneCallState.Resuming -> {
            baseState
        }

        is LinphoneCallState.Referred -> {
            baseState
        }

        is LinphoneCallState.Error -> {
            baseState.copy(
                callState = CallStateConfig.UI_ERROR
            )
        }

        is LinphoneCallState.End -> {
            timer.stop()
            baseState.copy(
                callState = CallStateConfig.UI_END
            )
        }

        is LinphoneCallState.PausedByRemote -> {
            timer.pause()
            baseState.copy(
                callState = CallStateConfig.UI_PAUSED
            )
        }

        is LinphoneCallState.UpdatedByRemote -> {
            baseState
        }

        is LinphoneCallState.IncomingEarlyMedia -> {
            baseState
        }

        is LinphoneCallState.Updating -> {
            baseState
        }

        is LinphoneCallState.Released -> {
            baseState.copy(
                isCallEnded = true,
            )
        }

        is LinphoneCallState.EarlyUpdatedByRemote -> {
            baseState
        }

        is LinphoneCallState.EarlyUpdating -> {
            baseState
        }

        is LinphoneCallState.Unknown -> {
            baseState
        }
    }
}


