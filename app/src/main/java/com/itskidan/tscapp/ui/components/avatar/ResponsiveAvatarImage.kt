package com.itskidan.tscapp.ui.components.avatar

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.itskidan.tscapp.ui.model.AvatarParams

@Composable
fun ResponsiveAvatarImage(
    avatarParams: AvatarParams
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    // Base size + limits
    val avatarSize = remember(screenWidth) {
        if (screenWidth < avatarParams.compactBreakpoint) avatarParams.minSize
        else minOf(avatarParams.maxSize, screenWidth * avatarParams.scale)
    }

    AvatarImage(
        image = avatarParams.image,
        size = avatarSize,
        modifier = Modifier
            .widthIn(avatarParams.minSize, avatarParams.maxSize)
            .heightIn(avatarParams.minSize, avatarParams.maxSize),
        placeholder = avatarParams.placeholder,
        contentDescription = avatarParams.description,
        contentScale = avatarParams.contentScale
    )
}