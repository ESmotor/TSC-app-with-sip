package com.itskidan.tscapp.ui.mapper

import com.itskidan.domain.model.CallDirection
import com.itskidan.domain.model.CallState
import com.itskidan.tscapp.ui.model.Timer
import com.itskidan.tscapp.ui.screens.call.CallStateConfig
import com.itskidan.tscapp.ui.screens.call.CallUiState

fun mapSysCallStateToText(state: CallState): String {
    return when (state) {
        is CallState.Idle -> CallStateConfig.SYS_IDLE
        is CallState.IncomingReceived -> CallStateConfig.SYS_INCOMING_RECEIVED
        is CallState.PushIncomingReceived -> CallStateConfig.SYS_PUSH_INCOMING_RECEIVED
        is CallState.OutgoingInit -> CallStateConfig.SYS_OUTGOING_INIT
        is CallState.OutgoingProgress -> CallStateConfig.SYS_OUTGOING_PROGRESS
        is CallState.OutgoingRinging -> CallStateConfig.SYS_OUTGOING_RINGING
        is CallState.OutgoingEarlyMedia -> CallStateConfig.SYS_OUTGOING_EARLY_MEDIA
        is CallState.Connected -> CallStateConfig.SYS_CONNECTED
        is CallState.StreamsRunning -> CallStateConfig.SYS_STREAMS_RUNNING
        is CallState.Pausing -> CallStateConfig.SYS_PAUSING
        is CallState.Paused -> CallStateConfig.SYS_PAUSED
        is CallState.Resuming -> CallStateConfig.SYS_RESUMING
        is CallState.Referred -> CallStateConfig.SYS_REFERRED
        is CallState.Error -> CallStateConfig.SYS_ERROR
        is CallState.End -> CallStateConfig.SYS_END
        is CallState.PausedByRemote -> CallStateConfig.SYS_PAUSED_BY_REMOTE
        is CallState.UpdatedByRemote -> CallStateConfig.SYS_UPDATED_BY_REMOTE
        is CallState.IncomingEarlyMedia -> CallStateConfig.SYS_INCOMING_EARLY_MEDIA
        is CallState.Updating -> CallStateConfig.SYS_UPDATING
        is CallState.Released -> CallStateConfig.SYS_RELEASED
        is CallState.EarlyUpdatedByRemote -> CallStateConfig.SYS_EARLY_UPDATED_BY_REMOTE
        is CallState.EarlyUpdating -> CallStateConfig.SYS_EARLY_UPDATING
        is CallState.Unknown -> CallStateConfig.SYS_UNKNOWN
    }

}


fun mapCallStateToUiState(
    currentState: CallUiState,
    callState: CallState,
    timer: Timer
): CallUiState {
    val baseState = currentState.copy(
        isOutgoingCall = callState.callInfo.callDirection == CallDirection.OUTGOING,
        remoteName = callState.callInfo.remoteName ?: "Unknown",

        )
    return when (callState) {
        is CallState.Idle -> {
            baseState
        }

        is CallState.IncomingReceived -> {
            baseState.copy(
                callState = CallStateConfig.UI_RINGING
            )
        }

        is CallState.PushIncomingReceived -> {
            baseState.copy(
                callState = CallStateConfig.UI_RINGING
            )
        }

        is CallState.OutgoingInit -> {
            baseState.copy(
                callState = CallStateConfig.UI_RINGING
            )
        }

        is CallState.OutgoingProgress -> {
            baseState.copy(
                callState = CallStateConfig.UI_RINGING
            )
        }

        is CallState.OutgoingRinging -> {
            baseState.copy(
                callState = CallStateConfig.UI_CONNECTING
            )
        }

        is CallState.OutgoingEarlyMedia -> {
            baseState
        }

        is CallState.Connected -> {
            baseState.copy(
                callState = CallStateConfig.UI_CONNECTING
            )
        }

        is CallState.StreamsRunning -> {
            if (!currentState.isRunningCall) {
                timer.start()
            } else {
                timer.resume()
            }
            baseState.copy(
                isRunningCall = true
            )
        }

        is CallState.Pausing -> {
            baseState
        }

        is CallState.Paused -> {
            timer.pause()
            baseState.copy(
                callState = CallStateConfig.UI_PAUSED
            )
        }

        is CallState.Resuming -> {
            baseState
        }

        is CallState.Referred -> {
            baseState
        }

        is CallState.Error -> {
            baseState.copy(
                callState = CallStateConfig.UI_ERROR
            )
        }

        is CallState.End -> {
            timer.stop()
            baseState.copy(
                callState = CallStateConfig.UI_END
            )
        }

        is CallState.PausedByRemote -> {
            timer.pause()
            baseState.copy(
                callState = CallStateConfig.UI_PAUSED
            )
        }

        is CallState.UpdatedByRemote -> {
            baseState
        }

        is CallState.IncomingEarlyMedia -> {
            baseState
        }

        is CallState.Updating -> {
            baseState
        }

        is CallState.Released -> {
            baseState.copy(
                isCallEnded = true,
            )
        }

        is CallState.EarlyUpdatedByRemote -> {
            baseState
        }

        is CallState.EarlyUpdating -> {
            baseState
        }

        is CallState.Unknown -> {
            baseState
        }
    }
}


