package com.itskidan.tscapp.ui.screens.outgoingcall.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.itskidan.tscapp.ui.components.ControlButton
import com.itskidan.tscapp.ui.screens.outgoingcall.OutgoingCallScreenConfig
import com.itskidan.tscapp.ui.screens.outgoingcall.CallUiState
import com.itskidan.tscapp.ui.theme.LocalPaddingValues

@Composable
fun CallControls(
    uiState: CallUiState,
    onSpeakerphoneClick: () -> Unit,
    onHangUpClick: () -> Unit,
    onMicrophoneClick: () -> Unit,
    onMessageClick: () -> Unit
) {

    val paddingValues = LocalPaddingValues.current

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
                    OutgoingCallScreenConfig.colorIconOn
                } else OutgoingCallScreenConfig.colorIconOff,
                contentDescription = stringResource(OutgoingCallScreenConfig.buttonDescSpeakerRes),
                backgroundColor = if (uiState.isSpeakerphone) {
                    OutgoingCallScreenConfig.colorControlButtonsOn
                } else OutgoingCallScreenConfig.colorControlButtonsOff
            )

            ControlButton(
                onClick = onHangUpClick,
                icon = Icons.Filled.CallEnd,
                iconColor = OutgoingCallScreenConfig.colorIconOff,
                contentDescription = stringResource(OutgoingCallScreenConfig.buttonDescHangUpRes),
                backgroundColor = OutgoingCallScreenConfig.colorHangUpButton
            )

            ControlButton(
                onClick = onMicrophoneClick,
                icon = Icons.Filled.MicOff,
                iconColor = if (uiState.isMute) {
                    OutgoingCallScreenConfig.colorIconOn
                } else OutgoingCallScreenConfig.colorIconOff,
                contentDescription = stringResource(OutgoingCallScreenConfig.buttonDescMicRes),
                backgroundColor = if (uiState.isMute) {
                    OutgoingCallScreenConfig.colorControlButtonsOn
                } else OutgoingCallScreenConfig.colorControlButtonsOff
            )

        }

        FilledTonalButton(
            onClick = onMessageClick,
            modifier = Modifier.width(OutgoingCallScreenConfig.messageButtonWidth),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = OutgoingCallScreenConfig.colorMessageButton,
                contentColor = OutgoingCallScreenConfig.colorContentMessageButton
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Message,
                contentDescription = null
            )
            Spacer(Modifier.width(paddingValues.small))
            Text(
                style = MaterialTheme.typography.labelLarge,
                text = stringResource(OutgoingCallScreenConfig.messageButtonText)
            )
        }
    }
}