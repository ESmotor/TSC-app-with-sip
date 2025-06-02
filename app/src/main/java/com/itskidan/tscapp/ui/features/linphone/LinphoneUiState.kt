package com.itskidan.tscapp.ui.features.linphone

data class LinphoneUiState(
    val callStateText: String = "Ready For Call",
    val coreStateText: String = "Core is Ready",
    val regStateText: String = "Registration Successful",
)