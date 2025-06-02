package com.itskidan.tscapp.ui.features.call

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.itskidan.tscapp.ui.common.avatar.getAvatarParams
import com.itskidan.tscapp.ui.components.avatar.ResponsiveAvatarImage
import com.itskidan.tscapp.ui.components.backgroundWithPattern
import com.itskidan.tscapp.ui.features.call.components.ControlButton
import com.itskidan.tscapp.ui.features.call.components.SwipeControlButton
import com.itskidan.tscapp.ui.features.call.components.SwipeTrailHint
import com.itskidan.tscapp.ui.features.call.model.CallUiState
import com.itskidan.tscapp.ui.model.AvatarParams
import com.itskidan.tscapp.ui.theme.LocalPaddingValues
import com.itskidan.tscapp.ui.theme.LocalWindowSizeClass
import com.itskidan.tscapp.ui.theme.PaddingValues

@Composable
fun CallScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: CallViewModel = hiltViewModel(),
    windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current
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

    val avatarParams = remember(windowSizeClass, uiState.contactAvatar) {
        getAvatarParams(windowSizeClass.widthSizeClass, uiState.contactAvatar)
    }


    CallScreenContent(
        avatarParams = avatarParams,
        uiState = uiState,
        modifier = Modifier.fillMaxSize(),
        onSpeakerphoneClick = { viewModel.toggleSpeakerphone() },
        onHangUpClick = { viewModel.hungUpCall() },
        onAnswerClick = { viewModel.answerCall() },
        onMicrophoneClick = { viewModel.toggleMute() },
        onMessageClick = { viewModel.sendMessage() }
    )
}


@Composable
private fun CallScreenContent(
    avatarParams: AvatarParams,
    uiState: CallUiState,
    modifier: Modifier = Modifier,
    onSpeakerphoneClick: () -> Unit,
    onHangUpClick: () -> Unit,
    onAnswerClick: () -> Unit,
    onMicrophoneClick: () -> Unit,
    onMessageClick: () -> Unit,
) {


    val paddingValues = LocalPaddingValues.current

    Box(
        modifier = modifier
            .backgroundWithPattern(
                patternImage = CallScreenConfig.callScreenBackgroundPattern,
                background = CallScreenConfig.colorBackgroundScreen,
                alpha = CallScreenConfig.PATTERN_ALPHA
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
                callerName = uiState.remoteName,
                callStatus = uiState.callState
            )

            CallControlsPanel(
                uiState = uiState,
                onSpeakerphoneClick = onSpeakerphoneClick,
                onHangUpClick = onHangUpClick,
                onMicrophoneClick = onMicrophoneClick,
                onMessageClick = onMessageClick,
                onAnswerClick = onAnswerClick
            )

        }
    }

}

@Composable
private fun CallHeader(
    modifier: Modifier,
    avatarParams: AvatarParams,
    callerName: String,
    callStatus: String
) {

    val paddingValues = LocalPaddingValues.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(paddingValues.medium)
        ) {
            Text(
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                color = CallScreenConfig.colorOutgoingCallName,
                text = callerName
            )
            ResponsiveAvatarImage(avatarParams = avatarParams)
        }
        Text(
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = CallScreenConfig.colorCallStatus,
            text = callStatus
        )
    }
}


@Composable
private fun CallControlsPanel(
    uiState: CallUiState,
    onSpeakerphoneClick: () -> Unit,
    onHangUpClick: () -> Unit,
    onMicrophoneClick: () -> Unit,
    onMessageClick: () -> Unit,
    onAnswerClick: () -> Unit
) {

    val paddingValues = LocalPaddingValues.current


    if (!uiState.isRunningCall && !uiState.isOutgoingCall) {
        IncomingCallControlButtons(
            paddingValues = paddingValues,
            uiState = uiState,
            onAnswerClick = onAnswerClick,
            onHangUpClick = onHangUpClick,
            onMessageClick = onMessageClick
        )
    } else {

        OutcomingCallControlButtons(
            paddingValues = paddingValues,
            uiState = uiState,
            onSpeakerphoneClick = onSpeakerphoneClick,
            onHangUpClick = onHangUpClick,
            onMicrophoneClick = onMicrophoneClick,
            onMessageClick = onMessageClick
        )
    }
}


@Composable
private fun IncomingCallControlButtons(
    paddingValues: PaddingValues,
    uiState: CallUiState,
    onAnswerClick: () -> Unit,
    onHangUpClick: () -> Unit,
    onMessageClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = paddingValues.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = paddingValues.large),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SwipeTrailHint()
                SwipeControlButton(
                    onSwipeComplete = onAnswerClick,
                    icon = Icons.Filled.Call,
                    iconColor = CallScreenConfig.colorIconOff,
                    contentDescription = stringResource(CallScreenConfig.buttonDescAnswerRes),
                    backgroundColor = CallScreenConfig.colorAnswerButton,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SwipeTrailHint()
                SwipeControlButton(
                    onSwipeComplete = onHangUpClick,
                    icon = Icons.Filled.CallEnd,
                    iconColor = CallScreenConfig.colorIconOff,
                    contentDescription = stringResource(CallScreenConfig.buttonDescHangUpRes),
                    backgroundColor = CallScreenConfig.colorHangUpButton
                )
            }

        }

        FilledTonalButton(
            onClick = onMessageClick,
            modifier = Modifier.width(CallScreenConfig.messageButtonWidth),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = CallScreenConfig.colorMessageButton,
                contentColor = CallScreenConfig.colorContentMessageButton
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Message,
                contentDescription = null
            )
            Spacer(Modifier.width(paddingValues.small))
            Text(
                style = MaterialTheme.typography.labelLarge,
                text = stringResource(CallScreenConfig.messageButtonText)
            )
        }
    }
}

@Composable
private fun OutcomingCallControlButtons(
    paddingValues: PaddingValues,
    uiState: CallUiState,
    onSpeakerphoneClick: () -> Unit,
    onHangUpClick: () -> Unit,
    onMicrophoneClick: () -> Unit,
    onMessageClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = paddingValues.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = paddingValues.large),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            ControlButton(
                onClick = onSpeakerphoneClick,
                icon = Icons.AutoMirrored.Filled.VolumeUp,
                iconColor = if (uiState.isSpeakerphone) {
                    CallScreenConfig.colorIconOn
                } else CallScreenConfig.colorIconOff,
                contentDescription = stringResource(CallScreenConfig.buttonDescSpeakerRes),
                backgroundColor = if (uiState.isSpeakerphone) {
                    CallScreenConfig.colorControlButtonsOn
                } else CallScreenConfig.colorControlButtonsOff
            )

            ControlButton(
                onClick = onHangUpClick,
                icon = Icons.Filled.CallEnd,
                iconColor = CallScreenConfig.colorIconOff,
                contentDescription = stringResource(CallScreenConfig.buttonDescHangUpRes),
                backgroundColor = CallScreenConfig.colorHangUpButton
            )

            ControlButton(
                onClick = onMicrophoneClick,
                icon = Icons.Filled.MicOff,
                iconColor = if (uiState.isMute) {
                    CallScreenConfig.colorIconOn
                } else CallScreenConfig.colorIconOff,
                contentDescription = stringResource(CallScreenConfig.buttonDescMicRes),
                backgroundColor = if (uiState.isMute) {
                    CallScreenConfig.colorControlButtonsOn
                } else CallScreenConfig.colorControlButtonsOff
            )

        }

        FilledTonalButton(
            onClick = onMessageClick,
            modifier = Modifier.width(CallScreenConfig.messageButtonWidth),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = CallScreenConfig.colorMessageButton,
                contentColor = CallScreenConfig.colorContentMessageButton
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Message,
                contentDescription = null
            )
            Spacer(Modifier.width(paddingValues.small))
            Text(
                style = MaterialTheme.typography.labelLarge,
                text = stringResource(CallScreenConfig.messageButtonText)
            )
        }
    }
}


@Composable
@Preview
fun IncomingCallScreenPreview() {
    val uiState = CallUiState(
        isOutgoingCall = false,
        callState = "Calling...",
        remoteName = "Timofei Smirnov",
        contactAvatar = "",
        isMute = true,
        isSpeakerphone = false,
        isCallEnded = false,
        isRunningCall = false
    )
    val userAvatar = uiState.contactAvatar
    val windowSizeClass = WindowWidthSizeClass.Compact
    val avatarParams = remember(windowSizeClass, userAvatar) {
        getAvatarParams(windowSizeClass, userAvatar)
    }


    CallScreenContent(
        avatarParams = avatarParams,
        uiState = uiState,
        modifier = Modifier.fillMaxSize(),
        onSpeakerphoneClick = { /*TODO*/ },
        onHangUpClick = { /*TODO*/ },
        onMicrophoneClick = { /*TODO*/ },
        onMessageClick = { /*TODO*/ },
        onAnswerClick = { /*TODO*/ }
    )
}

@Composable
@Preview(
    name = "Landscape Preview",
    widthDp = 800,
    heightDp = 360,
    showBackground = true
)
fun IncomingCallScreenLandscapePreview() {
    val uiState = CallUiState(
        isOutgoingCall = false,
        callState = "Calling...",
        remoteName = "Timofei Smirnov",
        contactAvatar = "",
        isMute = true,
        isSpeakerphone = false,
        isCallEnded = false,
        isRunningCall = false
    )
    val userAvatar = uiState.contactAvatar
    val windowSizeClass = WindowWidthSizeClass.Compact
    val avatarParams = remember(windowSizeClass, userAvatar) {
        getAvatarParams(windowSizeClass, userAvatar)
    }


    CallScreenContent(
        avatarParams = avatarParams,
        uiState = uiState,
        modifier = Modifier.fillMaxSize(),
        onSpeakerphoneClick = { /*TODO*/ },
        onHangUpClick = { /*TODO*/ },
        onMicrophoneClick = { /*TODO*/ },
        onMessageClick = { /*TODO*/ },
        onAnswerClick = { /*TODO*/ }
    )
}