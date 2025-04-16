package com.itskidan.domain.model.linphone


sealed class LinphoneCallState(
    val callDirection: CallDirection,
) {
    data class Idle(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class IncomingReceived(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class PushIncomingReceived(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class OutgoingInit(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class OutgoingProgress(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class OutgoingRinging(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class OutgoingEarlyMedia(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class Connected(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class StreamsRunning(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class Pausing(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class Paused(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class Resuming(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class Referred(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class Error(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class End(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class PausedByRemote(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class UpdatedByRemote(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class IncomingEarlyMedia(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class Updating(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class Released(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class EarlyUpdatedByRemote(
        val callDir: CallDirection,
    ) :
        LinphoneCallState(callDir)

    data class EarlyUpdating(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)

    data class Unknown(
        val callDir: CallDirection,
    ) : LinphoneCallState(callDir)
}