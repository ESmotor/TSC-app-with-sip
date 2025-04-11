package com.itskidan.data.linphone

import com.itskidan.domain.model.linphone.LinphoneCoreState
import com.itskidan.domain.repository.linphone.LinphoneCoreStatusObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.linphone.core.Core
import org.linphone.core.CoreListenerStub
import org.linphone.core.GlobalState
import timber.log.Timber
import javax.inject.Inject

class LinphoneCoreStatusObserverImpl @Inject constructor(
    core: Core
) : LinphoneCoreStatusObserver {
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
        })
    }
}