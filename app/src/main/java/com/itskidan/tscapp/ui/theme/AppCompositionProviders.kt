package com.itskidan.tscapp.ui.theme

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass> {
    error("No WindowSizeClass provided")
}
val LocalShapes = staticCompositionLocalOf { Shapes() }
val LocalPaddingValues = staticCompositionLocalOf { PaddingValues() }

@Composable
fun AppCompositionProviders(
    windowSizeClass: WindowSizeClass,
    content: @Composable () -> Unit
) {
    val insets = WindowInsets
    val statusBars = insets.statusBars.asPaddingValues().calculateTopPadding()
    val navigationBars = insets.navigationBars.asPaddingValues().calculateBottomPadding()

    val adaptivePadding = adaptivePaddingValues(windowSizeClass).copy(
        statusBars = statusBars,
        navigationBars = navigationBars
    )
    CompositionLocalProvider(
        LocalPaddingValues provides adaptivePadding,
        LocalWindowSizeClass provides windowSizeClass,
        LocalShapes provides adaptiveShapes(windowSizeClass),
        content = content
    )
}