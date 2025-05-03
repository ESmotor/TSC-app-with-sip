package com.itskidan.domain.model

sealed class SipRegistrationState {
    object None : SipRegistrationState()
    object Progress : SipRegistrationState()
    object Ok : SipRegistrationState()
    object Cleared : SipRegistrationState()
    object Failed : SipRegistrationState()
    object Refreshing : SipRegistrationState()
    object Unknown : SipRegistrationState()
}