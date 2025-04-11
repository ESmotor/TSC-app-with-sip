package com.itskidan.domain.model.linphone

sealed class LinphoneCoreState {
    object Off : LinphoneCoreState()
    object Startup : LinphoneCoreState()
    object On : LinphoneCoreState()
    object Shutdown : LinphoneCoreState()
    object Configuring : LinphoneCoreState()
    object Ready : LinphoneCoreState()
    object Unknown : LinphoneCoreState()
}