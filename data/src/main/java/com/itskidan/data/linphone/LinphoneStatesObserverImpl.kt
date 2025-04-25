package com.itskidan.data.linphone

import com.itskidan.domain.PushNotifier
import com.itskidan.domain.model.linphone.CallDirection
import com.itskidan.domain.model.linphone.LinphoneCallState
import com.itskidan.domain.model.linphone.LinphoneCoreState
import com.itskidan.domain.model.linphone.LinphoneRegState
import com.itskidan.domain.repository.FcmRepository
import com.itskidan.domain.repository.linphone.LinphoneStatesObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.linphone.core.Call
import org.linphone.core.Core
import org.linphone.core.CoreListenerStub
import org.linphone.core.GlobalState
import org.linphone.core.ProxyConfig
import org.linphone.core.RegistrationState
import timber.log.Timber
import javax.inject.Inject

class LinphoneStatesObserverImpl @Inject constructor(
    core: Core,
    private val notifier: PushNotifier,
    private val fcmRepository: FcmRepository
) : LinphoneStatesObserver {
    private val _callState =
        MutableStateFlow<LinphoneCallState>(LinphoneCallState.Idle(callDir = CallDirection.UNKNOWN))
    override val callState: Flow<LinphoneCallState> = _callState.asStateFlow()

    private val _regState = MutableStateFlow<LinphoneRegState>(LinphoneRegState.Unknown)
    override val registrationState: Flow<LinphoneRegState> = _regState.asStateFlow()

    private val _coreState = MutableStateFlow<LinphoneCoreState>(LinphoneCoreState.Unknown)
    override val coreState: Flow<LinphoneCoreState> = _coreState.asStateFlow()


    init {
        core.addListener(object : CoreListenerStub() {

            override fun onGlobalStateChanged(
                core: Core,
                state: GlobalState?,
                message: String
            ) {
                Timber.tag("MyLog").d("onGlobalStateChanged: $state")
                _coreState.value = when (state) {
                    GlobalState.Off -> LinphoneCoreState.Off
                    GlobalState.Startup -> LinphoneCoreState.Startup
                    GlobalState.On -> LinphoneCoreState.On
                    GlobalState.Shutdown -> LinphoneCoreState.Shutdown
                    GlobalState.Configuring -> LinphoneCoreState.Configuring
                    GlobalState.Ready -> LinphoneCoreState.Ready
                    null -> LinphoneCoreState.Unknown
                }
            }

            override fun onRegistrationStateChanged(
                core: Core,
                proxyConfig: ProxyConfig,
                state: RegistrationState?,
                message: String
            ) {
                Timber.tag("MyLog").d("onRegistrationStateChanged: $state, message: $message")
                _regState.value = when (state) {
                    RegistrationState.None -> LinphoneRegState.None
                    RegistrationState.Progress -> LinphoneRegState.Progress
                    RegistrationState.Ok -> LinphoneRegState.Ok
                    RegistrationState.Cleared -> LinphoneRegState.Cleared
                    RegistrationState.Failed -> LinphoneRegState.Failed
                    RegistrationState.Refreshing -> LinphoneRegState.Refreshing
                    null -> LinphoneRegState.Unknown
                }
            }

            override fun onCallStateChanged(
                core: Core,
                call: Call,
                state: Call.State?,
                message: String
            ) {
                Timber.tag("MyLog")
                    .d("onCallStateChanged: Dir:${call.dir}, state: $state, status: ${call.callLog.status}")

                val callDir = when (call.dir) {
                    Call.Dir.Outgoing -> CallDirection.OUTGOING
                    Call.Dir.Incoming -> {
                        CallDirection.INCOMING
                    }

                    else -> CallDirection.UNKNOWN
                }

                _callState.value = when (state) {
                    Call.State.Idle -> LinphoneCallState.Idle(callDir = callDir)
                    Call.State.IncomingReceived -> {
                        Timber.tag("MyLog").d(">>>>>>>>>> Incoming Received")
                        CoroutineScope(Dispatchers.IO).launch {
                            val token = fcmRepository.getFcmToken()
                            Timber.tag("MyLog").d("Token Incoming Received: $token")
                            if (token != null) {
                                notifier.notifyIncomingCall(
                                    token = token,
                                    caller = "Valera Smirnov"
                                )
                            }
                        }

                        LinphoneCallState.IncomingReceived(callDir = callDir)
                    }

                    Call.State.PushIncomingReceived -> {
                        Timber.tag("MyLog").d(">>>>>>>>>> Push Incoming Received")
                        LinphoneCallState.PushIncomingReceived(callDir = callDir)
                    }

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
                    Call.State.End -> LinphoneCallState.End(callDir = callDir)
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