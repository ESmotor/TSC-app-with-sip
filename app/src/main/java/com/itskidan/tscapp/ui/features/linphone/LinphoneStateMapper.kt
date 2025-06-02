package com.itskidan.tscapp.ui.features.linphone

import com.itskidan.domain.model.CallState
import com.itskidan.tscapp.ui.features.call.CallStateConfig

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
