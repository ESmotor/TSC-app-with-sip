package com.itskidan.domain.repository.linphone

interface LinphoneRepository {
    suspend fun makeOutgoingCall(phoneNumber: String): Result<Unit>
    suspend fun hangUpCall(): Result<Unit>
    suspend fun registerAccount(username: String , domain: String , password: String, transportType: Int ): Result<Unit>
}