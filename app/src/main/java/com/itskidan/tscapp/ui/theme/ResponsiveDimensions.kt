package com.itskidan.tscapp.ui.theme

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

fun adaptivePaddingValues(windowSizeClass: WindowSizeClass): PaddingValues =
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> CompactPadding
        WindowWidthSizeClass.Medium -> MediumPadding
        WindowWidthSizeClass.Expanded -> ExpandedPadding
        else -> CompactPadding
    }

fun adaptiveShapes(windowSizeClass: WindowSizeClass): Shapes =
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> CompactShapes
        WindowWidthSizeClass.Medium -> MediumShapes
        WindowWidthSizeClass.Expanded -> ExpandedShapes
        else -> CompactShapes
    }


@Composable
fun rememberAdaptivePaddingValues(windowSizeClass: WindowWidthSizeClass): PaddingValues {
    val statusBars = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navigationBars = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    return remember(windowSizeClass, statusBars, navigationBars) {
        when (windowSizeClass) {
            WindowWidthSizeClass.Compact -> PaddingValues(
                none = 0.dp,
                extraSmall = 4.dp,
                small = 8.dp,
                medium = 16.dp,
                large = 24.dp,
                extraLarge = 32.dp,
                statusBars = statusBars,
                navigationBars = navigationBars
            )

            WindowWidthSizeClass.Medium -> PaddingValues(
                none = 0.dp,
                extraSmall = 6.dp,
                small = 12.dp,
                medium = 20.dp,
                large = 28.dp,
                extraLarge = 40.dp,
                statusBars = statusBars,
                navigationBars = navigationBars
            )

            WindowWidthSizeClass.Expanded -> PaddingValues(
                none = 0.dp,
                extraSmall = 8.dp,
                small = 16.dp,
                medium = 24.dp,
                large = 32.dp,
                extraLarge = 48.dp,
                statusBars = statusBars,
                navigationBars = navigationBars
            )

            else -> PaddingValues(
                none = 0.dp,
                extraSmall = 4.dp,
                small = 8.dp,
                medium = 16.dp,
                large = 24.dp,
                extraLarge = 32.dp,
                statusBars = statusBars,
                navigationBars = navigationBars
            )
        }
    }
}

@Composable
fun rememberAdaptiveShapes(windowSizeClass: WindowWidthSizeClass): Shapes {
    return remember(windowSizeClass) {
        when (windowSizeClass) {
            WindowWidthSizeClass.Compact -> Shapes(
                none = 0.dp,
                extraSmall = 4.dp,
                small = 8.dp,
                medium = 12.dp,
                large = 16.dp,
                extraLarge = 28.dp,
            )

            WindowWidthSizeClass.Medium -> Shapes(
                none = 0.dp,
                extraSmall = 6.dp,
                small = 10.dp,
                medium = 14.dp,
                large = 20.dp,
                extraLarge = 32.dp,
            )

            WindowWidthSizeClass.Expanded -> Shapes(
                none = 0.dp,
                extraSmall = 8.dp,
                small = 12.dp,
                medium = 16.dp,
                large = 24.dp,
                extraLarge = 40.dp,
            )

            else -> Shapes()
        }
    }
}