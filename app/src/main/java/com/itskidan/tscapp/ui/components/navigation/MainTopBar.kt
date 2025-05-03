package com.itskidan.tscapp.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(onMenuClick: () -> Unit) {
    Timber.tag("MyLog").d("MainTopBar Composed")
    TopAppBar(
        modifier = Modifier
            .clickable { Timber.tag("MyLog").d("TopBar Clicked") }
            .background(Color.Red),
        title = { Text("Main Screen") },
        navigationIcon = {
            IconButton(onClick = {
                Timber.tag("MyLog").d("OnClick")
                onMenuClick()
            }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        }
    )
}