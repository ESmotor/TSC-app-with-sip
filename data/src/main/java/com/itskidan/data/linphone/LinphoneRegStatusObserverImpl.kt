package com.itskidan.data.linphone

import com.itskidan.domain.model.linphone.LinphoneRegState
import com.itskidan.domain.repository.linphone.LinphoneRegStatusObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.linphone.core.Core
import org.linphone.core.CoreListenerStub
import org.linphone.core.ProxyConfig
import org.linphone.core.RegistrationState
import timber.log.Timber
import javax.inject.Inject

class LinphoneRegStatusObserverImpl @Inject constructor(
    core: Core
) : LinphoneRegStatusObserver {
    private val _regState = MutableStateFlow<LinphoneRegState>(LinphoneRegState.Unknown)
    override val registrationState: Flow<LinphoneRegState> = _regState.asStateFlow()

    init {
        core.addListener(object : CoreListenerStub() {
            override fun onRegistrationStateChanged(
                core: Core,
                cfg: ProxyConfig,
                state: RegistrationState?,
                message: String
            ) {
                Timber.tag("MyLog").d("onRegistrationStateChanged: $state")
                Timber.tag("MyLog").d("onRegStateMessage: $message")
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
        })
    }
}