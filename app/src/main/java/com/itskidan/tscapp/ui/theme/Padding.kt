package com.itskidan.tscapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class PaddingValues(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 32.dp,
    val statusBars: Dp = 0.dp,
    val navigationBars: Dp = 0.dp
)

val CompactPadding = PaddingValues()

val MediumPadding = PaddingValues(
    extraSmall = 6.dp,
    small = 12.dp,
    medium = 20.dp,
    large = 32.dp,
    extraLarge = 40.dp
)

val ExpandedPadding = PaddingValues(
    extraSmall = 8.dp,
    small = 16.dp,
    medium = 24.dp,
    large = 40.dp,
    extraLarge = 56.dp
)