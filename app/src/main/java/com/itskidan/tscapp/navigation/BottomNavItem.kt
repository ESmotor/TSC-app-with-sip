package com.itskidan.tscapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("Главная", Icons.Default.Home)
    object Directory : BottomNavItem("Справочник", Icons.AutoMirrored.Filled.MenuBook)
    object Help : BottomNavItem("Помощь", Icons.Default.School)
    object Events : BottomNavItem("Мероприятия", Icons.Default.Event)
    object More : BottomNavItem("Ещё", Icons.Default.MoreHoriz)
}