package com.itskidan.tscapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("Home", Icons.Default.Home)
    object Directory : BottomNavItem("Directory", Icons.AutoMirrored.Filled.MenuBook)
    object Help : BottomNavItem("Help", Icons.Default.School)
    object Events : BottomNavItem("Events", Icons.Default.Event)
    object More : BottomNavItem("More", Icons.Default.MoreHoriz)
}