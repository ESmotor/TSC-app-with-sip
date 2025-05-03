package com.itskidan.tscapp.ui.mapper

import com.itskidan.domain.model.SipRegistrationState

fun mapRegistrationStateToText(state: SipRegistrationState): String {
    return when (state) {
        SipRegistrationState.Cleared -> "LinphoneRegistrationState_Cleared"
        SipRegistrationState.Failed -> "LinphoneRegistrationState_Failed"
        SipRegistrationState.None -> "LinphoneRegistrationState_None"
        SipRegistrationState.Ok -> "LinphoneRegistrationState_Ok"
        SipRegistrationState.Progress -> "LinphoneRegistrationState_Progress"
        SipRegistrationState.Refreshing -> "LinphoneRegistrationState_Refreshing"
        SipRegistrationState.Unknown -> "LinphoneRegistrationState_Unknown"
    }}