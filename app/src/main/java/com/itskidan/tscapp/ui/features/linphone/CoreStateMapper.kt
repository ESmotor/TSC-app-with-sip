package com.itskidan.tscapp.ui.features.linphone

import com.itskidan.domain.model.CoreState

fun mapCoreStateToText(state: CoreState): String {
    return when (state) {
        CoreState.Configuring -> "LinphoneCoreState_Configuring"
        CoreState.Off -> "LinphoneCoreState_Off"
        CoreState.On -> "LinphoneCoreState_On"
        CoreState.Ready -> "LinphoneCoreState_Ready"
        CoreState.Shutdown -> "LinphoneCoreState_Shutdown"
        CoreState.Startup -> "LinphoneCoreState_Startup"
        CoreState.Unknown -> "LinphoneCoreState_Unknown"
    }
}