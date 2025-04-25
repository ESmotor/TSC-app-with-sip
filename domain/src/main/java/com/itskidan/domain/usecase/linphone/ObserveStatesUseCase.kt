package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.model.linphone.LinphoneCallState
import com.itskidan.domain.model.linphone.LinphoneCoreState
import com.itskidan.domain.model.linphone.LinphoneRegState
import com.itskidan.domain.repository.linphone.LinphoneStatesObserver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveStatesUseCase @Inject constructor(
    private val linphoneStatesObserver: LinphoneStatesObserver
) {
    fun getCallState(): Flow<LinphoneCallState> = linphoneStatesObserver.callState
    fun getRegState(): Flow<LinphoneRegState> = linphoneStatesObserver.registrationState
    fun getCoreState(): Flow<LinphoneCoreState> = linphoneStatesObserver.coreState
}