package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.model.CallState
import com.itskidan.domain.model.CoreState
import com.itskidan.domain.model.SipRegistrationState
import com.itskidan.domain.repository.SipStatesObserver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveStatesUseCase @Inject constructor(
    private val sipStatesObserver: SipStatesObserver
) {
    fun getCallState(): Flow<CallState> = sipStatesObserver.callState
    fun getRegState(): Flow<SipRegistrationState> = sipStatesObserver.registrationState
    fun getCoreState(): Flow<CoreState> = sipStatesObserver.coreState
}