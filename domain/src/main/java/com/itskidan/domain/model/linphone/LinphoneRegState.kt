package com.itskidan.domain.model.linphone

sealed class LinphoneRegState {
    object None : LinphoneRegState()
    object Progress : LinphoneRegState()
    object Ok : LinphoneRegState()
    object Cleared : LinphoneRegState()
    object Failed : LinphoneRegState()
    object Refreshing : LinphoneRegState()
    object Unknown : LinphoneRegState()
}