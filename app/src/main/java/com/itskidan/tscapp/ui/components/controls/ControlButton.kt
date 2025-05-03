package com.itskidan.tscapp.ui.components.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.itskidan.tscapp.ui.screens.call.CallScreenConfig

@Composable
fun ControlButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    iconColor: Color,
    backgroundColor: Color = Color.Transparent,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(CallScreenConfig.controlButtonSize)
            .background(backgroundColor, CircleShape)

    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(CallScreenConfig.controlIconSize),
            tint = iconColor
        )
    }
}


