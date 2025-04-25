package com.itskidan.tscapp.ui.screens.call.outgoingcall

object CallStateConfig{
    // System call states
    const val SYS_IDLE = "Idle"
    const val SYS_INCOMING_RECEIVED = "Incoming Received"
    const val SYS_PUSH_INCOMING_RECEIVED = "Push Incoming Received"
    const val SYS_OUTGOING_INIT = "Outgoing Init"
    const val SYS_OUTGOING_PROGRESS = "Outgoing Progress"
    const val SYS_OUTGOING_RINGING = "Outgoing Ringing"
    const val SYS_OUTGOING_EARLY_MEDIA = "Outgoing Early Media"
    const val SYS_CONNECTED = "Connected"
    const val SYS_STREAMS_RUNNING = "Streams Running"
    const val SYS_PAUSING = "Pausing"
    const val SYS_PAUSED = "Paused"
    const val SYS_RESUMING = "Resuming"
    const val SYS_REFERRED = "Referred"
    const val SYS_ERROR = "Error"
    const val SYS_END = "End"
    const val SYS_PAUSED_BY_REMOTE = "Paused By Remote"
    const val SYS_UPDATED_BY_REMOTE = "Updated By Remote"
    const val SYS_INCOMING_EARLY_MEDIA = "Incoming Early Media"
    const val SYS_UPDATING = "Updating"
    const val SYS_RELEASED = "Released"
    const val SYS_EARLY_UPDATED_BY_REMOTE = "Early Updated By Remote"
    const val SYS_EARLY_UPDATING = "Early Updating"
    const val SYS_UNKNOWN = "Unknown"

    // UI call states
    const val UI_RINGING = "Ringing"
    const val UI_CONNECTING = "Connecting"
    const val UI_RUNNING = "Running"
    const val UI_PAUSED = "Paused"
    const val UI_END = "Ended"
    const val UI_ERROR = "Error"
}