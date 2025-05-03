package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.repository.SipRepository
import javax.inject.Inject

class MakeCallUseCase @Inject constructor(
    private val sipRepository: SipRepository
) {
    suspend operator fun invoke(phoneNumber: String): Result<Unit> {
        // Additional logic if needed
        return sipRepository.makeOutgoingCall(phoneNumber)
    }
}