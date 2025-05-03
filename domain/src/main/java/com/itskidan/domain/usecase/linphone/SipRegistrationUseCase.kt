package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.repository.SipRepository
import javax.inject.Inject

class SipRegistrationUseCase @Inject constructor(
    private val sipRepository: SipRepository
) {
    suspend operator fun invoke(username: String, domain: String, password: String, transportType: Int): Result<Unit> {
        // Additional logic if needed
        return sipRepository.registerAccount(username, domain, password, transportType)
    }
}