package com.itskidan.tscapp.ui.features.home.components.services

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Expanded
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Medium
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

object ServicesConfig {
    val Compact = ServiceGridConfig(
        gridHeight = 276.dp,
        rowsNumber = 2,
        cardHeight = 130.dp,
        cardWidth = 130.dp,
        iconSize = DpSize(32.dp, 32.dp),
        spaceBetween = 8.dp,
    )
    val Medium = ServiceGridConfig(
        gridHeight = 356.dp,
        rowsNumber = 2,
        cardHeight = 170.dp,
        cardWidth = 170.dp,
        iconSize = DpSize(40.dp, 40.dp),
        spaceBetween = 8.dp
    )
    val Expanded = ServiceGridConfig(
        gridHeight = 240.dp,
        rowsNumber = 1,
        cardHeight = 240.dp,
        cardWidth = 240.dp,
        iconSize = DpSize(48.dp, 48.dp),
        spaceBetween = 8.dp
    )
}


data class ServiceGridConfig(
    val gridHeight:Dp,
    val rowsNumber: Int,
    val cardHeight: Dp,
    val cardWidth: Dp,
    val iconSize: DpSize,
    val spaceBetween: Dp,
)

fun getServicesConfig(windowWidthSizeClass: WindowWidthSizeClass): ServiceGridConfig {
    return when (windowWidthSizeClass) {
        Compact -> ServicesConfig.Compact
        Medium -> ServicesConfig.Medium
        Expanded -> ServicesConfig.Expanded
        else -> ServicesConfig.Compact
    }
}