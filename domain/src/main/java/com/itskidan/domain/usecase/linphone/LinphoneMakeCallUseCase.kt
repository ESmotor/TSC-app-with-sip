package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.repository.linphone.LinphoneRepository
import javax.inject.Inject

class LinphoneMakeCallUseCase @Inject constructor(
    private val linphoneRepository: LinphoneRepository
) {
    suspend operator fun invoke(phoneNumber: String): Result<Unit> {
        // Additional logic if needed
        return linphoneRepository.makeOutgoingCall(phoneNumber)
    }
}