package com.itskidan.tscapp.ui.features.home.components.services.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.filled.Speaker
import androidx.compose.material.icons.filled.Timer
import com.itskidan.domain.model.Service
import com.itskidan.tscapp.ui.features.home.components.services.model.ServiceUi

fun Service.toUi(): ServiceUi {
    val icon = when (iconName.lowercase()) {
        "ic_notifications" -> Icons.Default.Notifications
        "ic_home" -> Icons.Default.Home
        "ic_timer" -> Icons.Default.Timer
        "ic_speaker" -> Icons.Default.Speaker
        "ic_outbox" -> Icons.Default.Outbox
        "ic_delete" -> Icons.Default.Delete
        "ic_accountBalance" -> Icons.Default.AccountBalance
        else -> Icons.Default.Info
    }

    return ServiceUi(
        id = id,
        title = title,
        description = description,
        icon = icon
    )
}