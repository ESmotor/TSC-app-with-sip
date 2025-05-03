package com.itskidan.data.linphone

import com.itskidan.domain.PushNotifier
import com.itskidan.domain.model.CallInfo
import com.itskidan.domain.model.CallState
import com.itskidan.domain.model.CoreState
import com.itskidan.domain.model.SipRegistrationState
import com.itskidan.domain.repository.FcmRepository
import com.itskidan.domain.repository.SipStatesObserver
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
) : SipStatesObserver {
    private val _callState = MutableStateFlow<CallState>(CallState.Idle(CallInfo()))
    override val callState: Flow<CallState> = _callState.asStateFlow()

    private val _regState = MutableStateFlow<SipRegistrationState>(SipRegistrationState.Unknown)
    override val registrationState: Flow<SipRegistrationState> = _regState.asStateFlow()

    private val _coreState = MutableStateFlow<CoreState>(CoreState.Unknown)
    override val coreState: Flow<CoreState> = _coreState.asStateFlow()


    init {
        core.addListener(object : CoreListenerStub() {

            override fun onGlobalStateChanged(
                core: Core,
                state: GlobalState?,
                message: String
            ) {
                Timber.tag("MyLog").d("onGlobalStateChanged: $state")
                _coreState.value = when (state) {
                    GlobalState.Off -> CoreState.Off
                    GlobalState.Startup -> CoreState.Startup
                    GlobalState.On -> CoreState.On
                    GlobalState.Shutdown -> CoreState.Shutdown
                    GlobalState.Configuring -> CoreState.Configuring
                    GlobalState.Ready -> CoreState.Ready
                    null -> CoreState.Unknown
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
                    RegistrationState.None -> SipRegistrationState.None
                    RegistrationState.Progress -> SipRegistrationState.Progress
                    RegistrationState.Ok -> SipRegistrationState.Ok
                    RegistrationState.Cleared -> SipRegistrationState.Cleared
                    RegistrationState.Failed -> SipRegistrationState.Failed
                    RegistrationState.Refreshing -> SipRegistrationState.Refreshing
                    null -> SipRegistrationState.Unknown
                }
            }

            override fun onCallStateChanged(
                core: Core,
                call: Call,
                state: Call.State?,
                message: String
            ) {

                Timber.tag("MyLog")
                    .d("onCallStateChanged: Dir:${call.dir}, state: $state,remoteName: ${call.remoteAddress.displayName}, isMuted: ${call.microphoneMuted},status: ${call.callLog.status}")


                _callState.value = when (state) {
                    Call.State.Idle -> CallState.Idle(call.toCallInfo())

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

                        CallState.IncomingReceived(call.toCallInfo())
                    }

                    Call.State.PushIncomingReceived -> {
                        Timber.tag("MyLog").d(">>>>>>>>>> Push Incoming Received")
                        CallState.PushIncomingReceived(call.toCallInfo())
                    }

                    Call.State.OutgoingInit -> CallState.OutgoingInit(call.toCallInfo())

                    Call.State.OutgoingProgress -> CallState.OutgoingProgress(call.toCallInfo())

                    Call.State.OutgoingRinging -> CallState.OutgoingRinging(call.toCallInfo())

                    Call.State.OutgoingEarlyMedia -> CallState.OutgoingEarlyMedia(call.toCallInfo())

                    Call.State.Connected -> CallState.Connected(call.toCallInfo())

                    Call.State.StreamsRunning -> CallState.StreamsRunning(call.toCallInfo())

                    Call.State.Pausing -> CallState.Pausing(call.toCallInfo())

                    Call.State.Paused -> CallState.Paused(call.toCallInfo())

                    Call.State.Resuming -> CallState.Resuming(call.toCallInfo())

                    Call.State.Referred -> CallState.Referred(call.toCallInfo())

                    Call.State.Error -> CallState.Error(call.toCallInfo())

                    Call.State.End -> CallState.End(call.toCallInfo())

                    Call.State.PausedByRemote -> CallState.PausedByRemote(call.toCallInfo())

                    Call.State.UpdatedByRemote -> CallState.UpdatedByRemote(call.toCallInfo())

                    Call.State.IncomingEarlyMedia -> CallState.IncomingEarlyMedia(call.toCallInfo())

                    Call.State.Updating -> CallState.Updating(call.toCallInfo())

                    Call.State.Released -> CallState.Released(call.toCallInfo())

                    Call.State.EarlyUpdatedByRemote -> CallState.EarlyUpdatedByRemote(call.toCallInfo())

                    Call.State.EarlyUpdating -> CallState.EarlyUpdating(call.toCallInfo())

                    null -> CallState.Unknown(call.toCallInfo())
                }
            }

        })
    }
}