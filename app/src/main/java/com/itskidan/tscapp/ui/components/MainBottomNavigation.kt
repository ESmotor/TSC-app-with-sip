package com.itskidan.tscapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.itskidan.tscapp.navigation.BottomNavItem

@Composable
fun MainBottomNavigation(onItemSelected: (BottomNavItem) -> Unit) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.Settings)

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = false, // Add logic to determine which element is selected
                onClick = { onItemSelected(item) }
            )
        }
    }
}