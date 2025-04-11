package com.itskidan.domain.model.linphone


sealed class LinphoneCallState(

) {
    data class Idle(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class IncomingReceived(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class PushIncomingReceived(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class OutgoingInit(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class OutgoingProgress(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class OutgoingRinging(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class OutgoingEarlyMedia(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class Connected(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class StreamsRunning(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class Pausing(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class Paused(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class Resuming(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class Referred(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class Error(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class End(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class PausedByRemote(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class UpdatedByRemote(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class IncomingEarlyMedia(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class Updating(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class Released(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class EarlyUpdatedByRemote(
        val callDir: CallDirection,
    ) :
        LinphoneCallState()

    data class EarlyUpdating(
        val callDir: CallDirection,
    ) : LinphoneCallState()

    data class Unknown(
        val callDir: CallDirection,
    ) : LinphoneCallState()
}