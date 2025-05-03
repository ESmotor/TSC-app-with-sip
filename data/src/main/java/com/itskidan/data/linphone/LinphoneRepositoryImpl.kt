package com.itskidan.data.linphone


import com.itskidan.domain.repository.SipRepository
import com.itskidan.domain.repository.SipStatesObserver
import org.linphone.core.Core
import org.linphone.core.Factory
import org.linphone.core.MediaEncryption
import org.linphone.core.TransportType
import timber.log.Timber
import javax.inject.Inject

class LinphoneRepositoryImpl @Inject constructor(
    private val core: Core,
    @Suppress("unused") private val statesObserver: SipStatesObserver,
) : SipRepository {

    init {
        core.isPushNotificationEnabled = true
        core.start()
    }

    override suspend fun makeOutgoingCall(phoneNumber: String): Result<Unit> = runCatching {
        // As for everything we need to get the SIP URI of the remote and convert it to an Address
        val remoteSipUri = "sip:$phoneNumber@tscturkey.3cx.com.tr"
        val remoteAddress = Factory.instance().createAddress(remoteSipUri)
        remoteAddress
            ?: return Result.failure(Exception("Invalid SIP URI")) // If address parsing fails, we can't continue with outgoing call process

        // We also need a CallParams object
        // Create call params expects a Call object for incoming calls, but for outgoing we must use null safely
        val params = core.createCallParams(null)
        params
            ?: return Result.failure(Exception("Call parameters could not be created")) // Same for params

        // We can now configure it
        // Here we ask for no encryption but we could ask for ZRTP/SRTP/DTLS
        params.mediaEncryption = MediaEncryption.None
        // If we wanted to start the call with video directly
        //params.enableVideo(true)

        // Finally we start the call
        core.inviteAddressWithParams(remoteAddress, params)
        // Call process can be followed in onCallStateChanged callback from core listener
    }

    override suspend fun hangUpCall(): Result<Unit> = runCatching {
        // Call termination logic
        if (core.callsNb == 0) return Result.success(Unit)

        // If the call state isn't paused, we can get it using core.currentCall
        val call = core.currentCall ?: core.calls[0]
        call ?: return Result.failure(Exception("Call not found"))

        // Terminating a call is quite simple
        call.terminate()
    }

    override suspend fun answerCall(): Result<Unit> = runCatching {
        core.currentCall?.accept()
    }

    override suspend fun registerAccount(
        username: String,
        domain: String,
        password: String,
        transport: Int
    ): Result<Unit> = runCatching {

        val transportType = when (transport) {
            1 -> TransportType.Udp
            2 -> TransportType.Tcp
            else -> TransportType.Tls
        }

        val authInfo = Factory.instance().createAuthInfo(
            username, null, password, null, null, domain, null
        )

        val accountParams = core.createAccountParams()

        val identity = Factory.instance().createAddress("sip:$username@$domain")
        accountParams.identityAddress = identity

        val serverAddress = Factory.instance().createAddress("sip:$domain:5061")
        serverAddress?.transport = transportType
        accountParams.serverAddress = serverAddress

        accountParams.isRegisterEnabled = true
        accountParams.transport = transportType

        accountParams.pushNotificationAllowed = true

        core.addAuthInfo(authInfo)
        val account = core.createAccount(accountParams)
        core.addAccount(account)
        core.defaultAccount = account

        if (!core.isPushNotificationAvailable) {
            Timber.tag("MyLog").d("Push is not available, Something is wrong with the push setup!")
        }

    }

// ... other methods implementing Linphone operations via core
}