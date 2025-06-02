package com.itskidan.tscapp.ui.features.call.mapper

import com.itskidan.domain.model.CallDirection
import com.itskidan.domain.model.CallState
import com.itskidan.tscapp.ui.features.call.CallStateConfig
import com.itskidan.tscapp.ui.features.call.model.CallUiState
import com.itskidan.tscapp.ui.features.call.model.Timer

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


