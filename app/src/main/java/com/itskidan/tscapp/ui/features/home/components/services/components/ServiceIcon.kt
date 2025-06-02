package com.itskidan.tscapp.ui.features.home.components.services.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.DpSize

@Composable
fun ServiceIcon(
    icon: ImageVector,
    iconSize: DpSize,
    description: String,
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier
            .size(iconSize),
        imageVector = icon,
        contentDescription = description
    )
}