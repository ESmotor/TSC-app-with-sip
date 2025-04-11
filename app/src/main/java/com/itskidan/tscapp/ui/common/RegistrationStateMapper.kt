package com.itskidan.tscapp.ui.common

import com.itskidan.domain.model.linphone.LinphoneRegState

fun mapRegistrationStateToText(state: LinphoneRegState): String {
    return when (state) {
        LinphoneRegState.Cleared -> "LinphoneRegistrationState_Cleared"
        LinphoneRegState.Failed -> "LinphoneRegistrationState_Failed"
        LinphoneRegState.None -> "LinphoneRegistrationState_None"
        LinphoneRegState.Ok -> "LinphoneRegistrationState_Ok"
        LinphoneRegState.Progress -> "LinphoneRegistrationState_Progress"
        LinphoneRegState.Refreshing -> "LinphoneRegistrationState_Refreshing"
        LinphoneRegState.Unknown -> "LinphoneRegistrationState_Unknown"
    }}