package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.repository.linphone.LinphoneRepository
import javax.inject.Inject

class LinphoneHangUpCallUseCase @Inject constructor(
    private val linphoneRepository: LinphoneRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        // Additional logic if needed
        return linphoneRepository.hangUpCall()
    }
}