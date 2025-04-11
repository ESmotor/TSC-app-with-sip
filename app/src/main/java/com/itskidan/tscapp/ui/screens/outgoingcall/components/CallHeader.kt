package com.itskidan.tscapp.ui.screens.outgoingcall.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.itskidan.tscapp.ui.components.avatar.ResponsiveAvatarImage
import com.itskidan.tscapp.ui.model.AvatarParams
import com.itskidan.tscapp.ui.screens.outgoingcall.OutgoingCallScreenConfig
import com.itskidan.tscapp.ui.theme.LocalPaddingValues

@Composable
fun CallHeader(
    modifier: Modifier,
    avatarParams: AvatarParams,
    outgoingCallName: String,
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
                color = OutgoingCallScreenConfig.colorOutgoingCallName,
                text = outgoingCallName
            )
            ResponsiveAvatarImage(avatarParams = avatarParams)
        }
        Text(
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = OutgoingCallScreenConfig.colorCallStatus,
            text = callStatus
        )
    }
}