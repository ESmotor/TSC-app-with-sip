package com.itskidan.domain.repository.linphone

import com.itskidan.domain.model.linphone.LinphoneCallState
import com.itskidan.domain.model.linphone.LinphoneCoreState
import com.itskidan.domain.model.linphone.LinphoneRegState
import kotlinx.coroutines.flow.Flow

interface LinphoneStatesObserver {
    val coreState: Flow<LinphoneCoreState>
    val registrationState: Flow<LinphoneRegState>
    val callState: Flow<LinphoneCallState>
}