package com.itskidan.tscapp.ui.features.linphone

import android.Manifest
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.itskidan.domain.model.DrawerItem
import com.itskidan.tscapp.navigation.BottomNavItem
import com.itskidan.tscapp.ui.features.call.components.ControlButton
import com.itskidan.tscapp.ui.features.call.components.SwipeControlButton
import com.itskidan.tscapp.ui.components.drawer.DrawerContent
import com.itskidan.tscapp.ui.components.navigation.BottomNavigation
import com.itskidan.tscapp.ui.components.navigation.MainTopBar
import com.itskidan.tscapp.ui.components.permissions.requestPermission
import com.itskidan.tscapp.ui.features.call.CallActivity
import com.itskidan.tscapp.ui.theme.AppTheme
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LinphoneStatusScreen(
    navController: NavHostController,
    viewModel: LinphoneStatusViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val items by viewModel.drawerItems.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val toastMessage by viewModel.toastMessage.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val micPermissionState = requestPermission(
        permission = Manifest.permission.RECORD_AUDIO,
        onGranted = { "Microphone permission is Granted" }, // we call immediately as soon as access is granted
        onDenied = {
            Toast.makeText(
                context,
                "Without access to the microphone, the call is not possible",
                Toast.LENGTH_SHORT
            ).show()
        }
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requestPermission(
            permission = Manifest.permission.POST_NOTIFICATIONS,
            onGranted = {},
            onDenied = {}
        )
    }


    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearToast()
        }
    }
    LinphoneStatusScreenContent(
        drawerState = drawerState,
        items = items,
        uiState = uiState,
        onDrawerSelected = viewModel::onDrawerSelected,
        onDrawerClose = { scope.launch { drawerState.close() } },
        onMenuClick = { scope.launch { drawerState.open() } },
        onBottomNavSelected = viewModel::onBottomNavSelected,
        onCallButtonClick = {
            if (micPermissionState.status.isGranted) {
                viewModel.onMakeCallClicked()
                val intent = Intent(context, CallActivity::class.java).apply {
                    // if you need to pass something to the activity
                    //putExtra("EXTRA_IS_INCOMING", false)......
                }
                context.startActivity(intent)
                Timber.Forest.tag("MyLog").d("[CallActivity] is started()")
            } else {
                micPermissionState.launchPermissionRequest()
            }
        },
        onActivateService = {
            viewModel.onActivateService()
        },
        onInfoClick = {
            viewModel.onInfoClick()
        },
        onDeleteClick = {
            viewModel.onDeleteClick()
        }
    )
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LinphoneStatusScreenContent(
    drawerState: DrawerState,
    items: List<DrawerItem>,
    uiState: LinphoneUiState,
    onDrawerSelected: (DrawerItem) -> Unit,
    onDrawerClose: () -> Unit,
    onMenuClick: () -> Unit,
    onBottomNavSelected: (BottomNavItem) -> Unit,
    onCallButtonClick: () -> Unit,
    onActivateService: () -> Unit,
    onInfoClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                items = items,
                onDrawerSelected = onDrawerSelected,
                onClose = onDrawerClose,
            )
        }
    ) {
        Scaffold(
            topBar = { MainTopBar { onMenuClick } },
            bottomBar = { BottomNavigation(onItemSelected = onBottomNavSelected) }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                LinphoneStatusTable(uiState = uiState)

                ControlButton(
                    onClick = onCallButtonClick,
                    icon = Icons.Filled.Call,
                    contentDescription = "Start Call",
                    iconColor = Color.White,
                    backgroundColor = Color(0xFF4CAF50),
                )
                Spacer(modifier = Modifier.padding(16.dp))
                ControlButton(
                    onClick = onActivateService,
                    icon = Icons.Filled.Timer,
                    contentDescription = "End Call",
                    iconColor = Color.White,
                    backgroundColor = Color(0xFFF44336),
                )
                Spacer(modifier = Modifier.padding(16.dp))
                ControlButton(
                    onClick = onInfoClick,
                    icon = Icons.Filled.Home,
                    contentDescription = "End Call",
                    iconColor = Color.White,
                    backgroundColor = Color(0xFF002288),
                )

                Spacer(modifier = Modifier.padding(16.dp))
                ControlButton(
                    onClick = onDeleteClick,
                    icon = Icons.Filled.DeleteForever,
                    contentDescription = "End Call",
                    iconColor = Color.White,
                    backgroundColor = Color(0xFF002288),
                )
                Spacer(modifier = Modifier.padding(16.dp))

                SwipeControlButton(
                    onSwipeComplete = {
                        Timber.Forest.tag("MyLog").d("[SwipeUpButton] onSwipeUp()")
                    },
                    icon = Icons.Filled.DeleteForever,
                    contentDescription = "End Call",
                    iconColor = Color.White,
                    backgroundColor = Color(0xFF002288),
                )

            }
        }
    }

}


@Composable
fun LinphoneStatusTable(
    uiState: LinphoneUiState,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = "Linphone Status Observer",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Status table
            StatusRow(
                label = "Linphone Core State",
                value = uiState.coreStateText
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            StatusRow(
                label = "Register Account State",
                value = uiState.regStateText
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            StatusRow(
                label = "Outgoing Call State",
                value = uiState.callStateText
            )
        }
    }
}

@Composable
fun StatusRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF4CAF50) // Green color for successful states
        )
    }
}


@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(411.dp, 891.dp))
    AppTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        LinphoneStatusScreenContent(
            drawerState = drawerState,
            items = listOf(),
            uiState = LinphoneUiState(),
            onDrawerSelected = {},
            onDrawerClose = {},
            onMenuClick = {},
            onBottomNavSelected = {},
            onCallButtonClick = {},
            onActivateService = {},
            onInfoClick = {},
            onDeleteClick = {}
        )
    }
}

