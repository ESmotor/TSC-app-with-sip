package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.model.linphone.LinphoneCallState
import com.itskidan.domain.repository.linphone.LinphoneCallStatusObserver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCallSateUseCase @Inject constructor(
    private val linphoneCallStatusObserver: LinphoneCallStatusObserver
) {
    fun execute(): Flow<LinphoneCallState> = linphoneCallStatusObserver.callState
}