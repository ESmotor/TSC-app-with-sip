package com.itskidan.domain.repository.linphone

import com.itskidan.domain.model.linphone.LinphoneRegState
import kotlinx.coroutines.flow.Flow

interface LinphoneRegStatusObserver {
    val registrationState: Flow<LinphoneRegState>
}