package com.itskidan.tscapp.ui.screens.outgoingcall

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.itskidan.tscapp.ui.common.avatar.getAvatarParams
import com.itskidan.tscapp.ui.common.background.backgroundWithPattern
import com.itskidan.tscapp.ui.model.AvatarParams
import com.itskidan.tscapp.ui.screens.outgoingcall.components.CallControls
import com.itskidan.tscapp.ui.screens.outgoingcall.components.CallHeader
import com.itskidan.tscapp.ui.theme.AppTheme
import com.itskidan.tscapp.ui.theme.LocalPaddingValues
import com.itskidan.tscapp.ui.theme.LocalWindowSizeClass

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun OutgoingCallScreen(
    navController: NavHostController,
    viewModel: OutgoingCallViewModel = hiltViewModel(),
    currentWindowSizeClass: WindowWidthSizeClass = LocalWindowSizeClass.current
) {
    val context = LocalContext.current

    val toastMessage by viewModel.toastMessage.collectAsStateWithLifecycle()
    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearToast()
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isCallEnded) {
        if (uiState.isCallEnded) {
            navController.popBackStack()
        }
    }

    val avatarParams = remember(currentWindowSizeClass, uiState.contactAvatar) {
        getAvatarParams(currentWindowSizeClass, uiState.contactAvatar)
    }

    OutgoingCallContent(
        avatarParams = avatarParams,
        uiState = uiState,
        modifier = Modifier.fillMaxSize(),
        onSpeakerphoneClick = { viewModel.toggleSpeakerphone() },
        onHangUpClick = {
            viewModel.hungUpCall()
        },
        onMicrophoneClick = { viewModel.toggleMute() },
        onMessageClick = { viewModel.sendMessage() }
    )

}

@Composable
private fun OutgoingCallContent(
    avatarParams: AvatarParams,
    uiState: CallUiState,
    modifier: Modifier = Modifier,
    onSpeakerphoneClick: () -> Unit,
    onHangUpClick: () -> Unit,
    onMicrophoneClick: () -> Unit,
    onMessageClick: () -> Unit
) {

    val paddingValues = LocalPaddingValues.current

    Box(
        modifier = modifier
            .backgroundWithPattern(
                patternImage = OutgoingCallScreenConfig.callScreenBackgroundPattern,
                background = OutgoingCallScreenConfig.colorBackgroundScreen,
                alpha = OutgoingCallScreenConfig.PATTERN_ALPHA
            )
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .padding(horizontal = paddingValues.medium, vertical = paddingValues.small)
                .matchParentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            CallHeader(
                modifier = Modifier.weight(1f),
                avatarParams = avatarParams,
                outgoingCallName = uiState.contactCaption,
                callStatus = uiState.callState
            )

            CallControls(
                uiState = uiState,
                onSpeakerphoneClick = onSpeakerphoneClick,
                onHangUpClick = onHangUpClick,
                onMicrophoneClick = onMicrophoneClick,
                onMessageClick = onMessageClick
            )

        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun OutgoingCallScreenPreview() {
    AppTheme {
        val uiState = CallUiState()
        val userAvatar = uiState.contactAvatar
        val windowSizeClass = WindowWidthSizeClass.Compact
        val avatarParams = remember(windowSizeClass, userAvatar) {
            getAvatarParams(windowSizeClass, userAvatar)
        }

        OutgoingCallContent(
            avatarParams = avatarParams,
            uiState = uiState,
            modifier = Modifier.fillMaxSize(),
            onSpeakerphoneClick = { /*TODO*/ },
            onHangUpClick = { /*TODO*/ },
            onMicrophoneClick = { /*TODO*/ },
            onMessageClick = { /*TODO*/ }
        )
    }
}