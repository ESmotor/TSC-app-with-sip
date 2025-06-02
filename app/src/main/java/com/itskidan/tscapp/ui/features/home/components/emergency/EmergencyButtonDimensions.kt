package com.itskidan.tscapp.ui.features.home.components.emergency

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Class for storing sizes depending on the screen category
data class EmergencyButtonDimensions(
    val containerHeight: Dp,
    val containerPadding: Dp,
    val buttonSize: Dp,
    val glowSize: Dp,
    val glowRadius: Float,
    val rippleRadius: Dp,
    val indicatorSize: Dp,
    val innerSize: Dp,
    val elevation: Dp,
    val innerElevation: Dp
) {
    companion object {
        fun compact() = EmergencyButtonDimensions(
            containerHeight = 180.dp,
            containerPadding = 16.dp,
            buttonSize = 150.dp,
            glowSize = 200.dp,
            glowRadius = 100f,
            rippleRadius = 75.dp,
            indicatorSize = 140.dp,
            innerSize = 90.dp,
            elevation = 8.dp,
            innerElevation = 6.dp
        )

        fun medium() = EmergencyButtonDimensions(
            containerHeight = 240.dp,
            containerPadding = 24.dp,
            buttonSize = 200.dp,
            glowSize = 250.dp,
            glowRadius = 125f,
            rippleRadius = 100.dp,
            indicatorSize = 190.dp,
            innerSize = 120.dp,
            elevation = 12.dp,
            innerElevation = 8.dp
        )

        fun expanded() = EmergencyButtonDimensions(
            containerHeight = 300.dp,
            containerPadding = 32.dp,
            buttonSize = 250.dp,
            glowSize = 300.dp,
            glowRadius = 150f,
            rippleRadius = 125.dp,
            indicatorSize = 240.dp,
            innerSize = 150.dp,
            elevation = 16.dp,
            innerElevation = 10.dp
        )
    }
}