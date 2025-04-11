package com.itskidan.domain.repository.linphone

import com.itskidan.domain.model.linphone.LinphoneCallState
import kotlinx.coroutines.flow.Flow

interface LinphoneCallStatusObserver {
    val callState: Flow<LinphoneCallState>
}