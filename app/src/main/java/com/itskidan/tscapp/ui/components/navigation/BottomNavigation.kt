package com.itskidan.tscapp.ui.components.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.itskidan.tscapp.navigation.BottomNavItem

@Composable
fun BottomNavigation(onItemSelected: (BottomNavItem) -> Unit) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Directory,
        BottomNavItem.Help,
        BottomNavItem.Events,
        BottomNavItem.More
    )
    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                selected = selectedItem == item, // Add logic to determine which element is selected
                onClick = {
                    selectedItem = item
                    onItemSelected(item)
                }
            )
        }
    }
}