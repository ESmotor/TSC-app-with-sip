package com.itskidan.domain.model


sealed class CallState {
    abstract val callInfo: CallInfo

    data class Idle(override val callInfo: CallInfo) : CallState()

    data class IncomingReceived(override val callInfo: CallInfo) : CallState()

    data class PushIncomingReceived(override val callInfo: CallInfo) : CallState()

    data class OutgoingInit(override val callInfo: CallInfo) : CallState()

    data class OutgoingProgress(override val callInfo: CallInfo) : CallState()

    data class OutgoingRinging(override val callInfo: CallInfo) : CallState()

    data class OutgoingEarlyMedia(override val callInfo: CallInfo) : CallState()

    data class Connected(override val callInfo: CallInfo) : CallState()

    data class StreamsRunning(override val callInfo: CallInfo) : CallState()

    data class Pausing(override val callInfo: CallInfo) : CallState()

    data class Paused(override val callInfo: CallInfo) : CallState()

    data class Resuming(override val callInfo: CallInfo) : CallState()

    data class Referred(override val callInfo: CallInfo) : CallState()

    data class Error(override val callInfo: CallInfo) : CallState()

    data class End(override val callInfo: CallInfo) : CallState()

    data class PausedByRemote(override val callInfo: CallInfo) : CallState()

    data class UpdatedByRemote(override val callInfo: CallInfo) : CallState()

    data class IncomingEarlyMedia(override val callInfo: CallInfo) : CallState()

    data class Updating(override val callInfo: CallInfo) : CallState()

    data class Released(override val callInfo: CallInfo) : CallState()

    data class EarlyUpdatedByRemote(override val callInfo: CallInfo) : CallState()

    data class EarlyUpdating(override val callInfo: CallInfo) : CallState()

    data class Unknown(override val callInfo: CallInfo) : CallState()
}