package com.itskidan.tscapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("Main", Icons.Default.Home)
    object Settings : BottomNavItem("Settings", Icons.Default.Settings)
}