package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.repository.SipRepository
import javax.inject.Inject

class AnswerCallUseCase @Inject constructor(
    private val sipRepository: SipRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        // Additional logic if needed
        return sipRepository.answerCall()
    }
}