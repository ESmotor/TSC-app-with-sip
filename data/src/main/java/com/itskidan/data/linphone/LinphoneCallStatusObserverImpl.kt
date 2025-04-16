package com.itskidan.data.linphone

import com.itskidan.domain.model.linphone.CallDirection
import com.itskidan.domain.model.linphone.LinphoneCallState
import com.itskidan.domain.repository.linphone.LinphoneCallStatusObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.linphone.core.Call
import org.linphone.core.Core
import org.linphone.core.CoreListenerStub
import timber.log.Timber
import javax.inject.Inject

class LinphoneCallStatusObserverImpl @Inject constructor(
    core: Core
) : LinphoneCallStatusObserver {
    private val _callState =
        MutableStateFlow<LinphoneCallState>(LinphoneCallState.Idle(callDir = CallDirection.UNKNOWN))
    override val callState: Flow<LinphoneCallState> = _callState.asStateFlow()

    init {
        core.addListener(object : CoreListenerStub() {

            override fun onCallStateChanged(
                core: Core,
                call: Call,
                state: Call.State?,
                message: String
            ) {
                Timber.tag("MyLog").d("onCallStateChanged: Dir:${call.dir}, state: $state, status: ${call.callLog.status}")

                val callDir = when (call.dir) {
                    Call.Dir.Outgoing -> CallDirection.OUTGOING
                    Call.Dir.Incoming -> CallDirection.INCOMING
                    else -> CallDirection.UNKNOWN
                }

                _callState.value = when (state) {
                    Call.State.Idle -> LinphoneCallState.Idle(callDir = callDir)
                    Call.State.IncomingReceived -> LinphoneCallState.IncomingReceived(callDir = callDir)
                    Call.State.PushIncomingReceived -> LinphoneCallState.PushIncomingReceived(callDir = callDir)
                    Call.State.OutgoingInit -> LinphoneCallState.OutgoingInit(callDir = callDir)
                    Call.State.OutgoingProgress -> LinphoneCallState.OutgoingProgress(callDir = callDir)
                    Call.State.OutgoingRinging -> LinphoneCallState.OutgoingRinging(callDir = callDir)
                    Call.State.OutgoingEarlyMedia -> LinphoneCallState.OutgoingEarlyMedia(callDir = callDir)
                    Call.State.Connected -> LinphoneCallState.Connected(callDir = callDir)
                    Call.State.StreamsRunning -> LinphoneCallState.StreamsRunning(callDir = callDir)
                    Call.State.Pausing -> LinphoneCallState.Pausing(callDir = callDir)
                    Call.State.Paused -> LinphoneCallState.Paused(callDir = callDir)
                    Call.State.Resuming -> LinphoneCallState.Resuming(callDir = callDir)
                    Call.State.Referred -> LinphoneCallState.Referred(callDir = callDir)
                    Call.State.Error -> LinphoneCallState.Error(callDir = callDir)
                    Call.State.End ->  LinphoneCallState.End(callDir = callDir)
                    Call.State.PausedByRemote -> LinphoneCallState.PausedByRemote(callDir = callDir)
                    Call.State.UpdatedByRemote -> LinphoneCallState.UpdatedByRemote(callDir = callDir)
                    Call.State.IncomingEarlyMedia -> LinphoneCallState.IncomingEarlyMedia(callDir = callDir)
                    Call.State.Updating -> LinphoneCallState.Updating(callDir = callDir)
                    Call.State.Released -> LinphoneCallState.Released(callDir = callDir)
                    Call.State.EarlyUpdatedByRemote -> LinphoneCallState.EarlyUpdatedByRemote(
                        callDir = callDir
                    )

                    Call.State.EarlyUpdating -> LinphoneCallState.EarlyUpdating(callDir = callDir)
                    null -> LinphoneCallState.Unknown(callDir = callDir)
                }
            }
        })
    }
}