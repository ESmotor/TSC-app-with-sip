package com.itskidan.tscapp.ui.features.home.components.services.styles

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

data class ServiceTextStyles(
    val title: TextStyle,
    val subtitle: TextStyle
)

@Composable
fun getServiceTextStyles(widthSizeClass: WindowWidthSizeClass): ServiceTextStyles {
    val typography = MaterialTheme.typography

    return when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> ServiceTextStyles(
            title = typography.titleMedium,
            subtitle = typography.bodyMedium
        )
        WindowWidthSizeClass.Medium -> ServiceTextStyles(
            title = typography.titleLarge,
            subtitle = typography.bodyLarge
        )
        WindowWidthSizeClass.Expanded -> ServiceTextStyles(
            title = typography.headlineSmall,
            subtitle = typography.titleMedium
        )
        else -> ServiceTextStyles(
            title = typography.titleMedium,
            subtitle = typography.bodyMedium
        )
    }
}