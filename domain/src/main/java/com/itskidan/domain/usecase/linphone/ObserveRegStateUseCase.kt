package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.model.linphone.LinphoneRegState
import com.itskidan.domain.repository.linphone.LinphoneRegStatusObserver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRegStateUseCase @Inject constructor(
    private val linphoneRegistrationStatusObserver: LinphoneRegStatusObserver
) {
    fun execute(): Flow<LinphoneRegState> =
        linphoneRegistrationStatusObserver.registrationState
}