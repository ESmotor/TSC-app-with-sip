package com.itskidan.tscapp.ui.screens.call.incomingcall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.itskidan.tscapp.ui.components.ControlButton
import com.itskidan.tscapp.ui.screens.call.outgoingcall.OutgoingCallViewModel
import com.itskidan.tscapp.ui.theme.LocalWindowSizeClass

@Composable
fun IncomingCallScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: OutgoingCallViewModel = hiltViewModel(),
    currentWindowSizeClass: WindowWidthSizeClass = LocalWindowSizeClass.current
) {
    IncomingCallScreenContent(
        onCallButtonClick = {
        }
    )
}


@Composable
fun IncomingCallScreenContent(
    onCallButtonClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ControlButton(
            onClick = onCallButtonClick,
            icon = Icons.Filled.Call,
            contentDescription = "Start Call",
            iconColor = Color.White,
            backgroundColor = Color(0xFF4CAF50),
        )
        ControlButton(
            onClick = onCallButtonClick,
            icon = Icons.Filled.Call,
            contentDescription = "Start Call",
            iconColor = Color.White,
            backgroundColor = Color(0xFF4CAF50),
        )
        ControlButton(
            onClick = onCallButtonClick,
            icon = Icons.Filled.Call,
            contentDescription = "Start Call",
            iconColor = Color.White,
            backgroundColor = Color(0xFF4CAF50),
        )
        ControlButton(
            onClick = onCallButtonClick,
            icon = Icons.Filled.Call,
            contentDescription = "Start Call",
            iconColor = Color.White,
            backgroundColor = Color(0xFF4CAF50),
        )
    }
}


@Composable
@Preview
fun IncomingCallScreenPreview() {
    IncomingCallScreenContent()
}
