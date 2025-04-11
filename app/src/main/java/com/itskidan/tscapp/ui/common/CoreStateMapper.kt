package com.itskidan.tscapp.ui.common

import com.itskidan.domain.model.linphone.LinphoneCoreState

fun mapCoreStateToText(state: LinphoneCoreState): String {
    return when (state) {
        LinphoneCoreState.Configuring -> "LinphoneCoreState_Configuring"
        LinphoneCoreState.Off -> "LinphoneCoreState_Off"
        LinphoneCoreState.On -> "LinphoneCoreState_On"
        LinphoneCoreState.Ready -> "LinphoneCoreState_Ready"
        LinphoneCoreState.Shutdown -> "LinphoneCoreState_Shutdown"
        LinphoneCoreState.Startup -> "LinphoneCoreState_Startup"
        LinphoneCoreState.Unknown -> "LinphoneCoreState_Unknown"
    }
}