package com.itskidan.domain.repository

import com.itskidan.domain.model.CallState
import com.itskidan.domain.model.CoreState
import com.itskidan.domain.model.SipRegistrationState
import kotlinx.coroutines.flow.Flow

interface SipStatesObserver {
    val coreState: Flow<CoreState>
    val registrationState: Flow<SipRegistrationState>
    val callState: Flow<CallState>
}