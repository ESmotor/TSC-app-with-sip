package com.itskidan.tscapp.ui.features.home.components.greeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.itskidan.tscapp.R
import com.itskidan.tscapp.ui.theme.LocalPaddingValues

@Composable
fun GreetingSection(
    userName: String,
    date: String,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val paddingValues = LocalPaddingValues.current
    val greetingText = stringResource(R.string.home_screen_greeting_hi_user, userName)
    val dateText = date
    val notificationDesc = stringResource(R.string.home_screen_greeting_notifications)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = paddingValues.medium, vertical = paddingValues.small),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = greetingText,
                style = MaterialTheme.typography.titleMedium
            )
            Text(dateText, style = MaterialTheme.typography.bodyMedium)
        }
        IconButton(
            onClick = onNotificationClick,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(Icons.Default.Notifications, contentDescription = notificationDesc)
        }
    }
}