package com.itskidan.tscapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestPermission(
    permission: String,
    autoLaunch: Boolean = false,
    onGranted: () -> Unit,
    onDenied: () -> Unit = {},
): PermissionState {
    val permissionState = rememberPermissionState(permission)

    LaunchedEffect(permissionState.status) {
        when {
            permissionState.status.isGranted -> onGranted()
            permissionState.status.shouldShowRationale -> onDenied()
        }
    }

    if (autoLaunch && !permissionState.status.isGranted) {
        LaunchedEffect(Unit) {
            permissionState.launchPermissionRequest()
        }
    }

    return permissionState
}