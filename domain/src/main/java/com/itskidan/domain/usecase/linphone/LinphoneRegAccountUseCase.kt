package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.repository.linphone.LinphoneRepository
import javax.inject.Inject

class LinphoneRegAccountUseCase @Inject constructor(
    private val linphoneRepository: LinphoneRepository
) {
    suspend operator fun invoke(username: String, domain: String, password: String, transportType: Int): Result<Unit> {
        // Additional logic if needed
        return linphoneRepository.registerAccount(username, domain, password, transportType)
    }
}