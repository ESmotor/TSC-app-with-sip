package com.itskidan.tscapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Shapes(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 28.dp,
)

val CompactShapes = Shapes()

val MediumShapes = Shapes(
    extraSmall = 6.dp,
    small = 10.dp,
    medium = 14.dp,
    large = 20.dp,
    extraLarge = 32.dp
)

val ExpandedShapes = Shapes(
    extraSmall = 8.dp,
    small = 12.dp,
    medium = 16.dp,
    large = 24.dp,
    extraLarge = 40.dp
)

