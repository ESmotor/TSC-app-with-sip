package com.itskidan.tscapp.ui.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Outbox
import androidx.compose.ui.graphics.vector.ImageVector

fun getFilledIconByName(iconName: String): ImageVector {
    return when (iconName.lowercase()) {
        "inbox" -> Icons.Filled.Inbox
        "outbox" -> Icons.Filled.Outbox
        "favorites" -> Icons.Filled.Favorite
        "trash" -> Icons.Filled.Delete
        else -> Icons.AutoMirrored.Filled.Help
    }
}

fun getOutlinedIconByName(iconName: String): ImageVector {
    return when (iconName.lowercase()) {
        "inbox" -> Icons.Outlined.Inbox
        "outbox" -> Icons.Outlined.Outbox
        "favorites" -> Icons.Outlined.FavoriteBorder
        "trash" -> Icons.Outlined.Delete
        else -> Icons.AutoMirrored.Outlined.Help
    }
}