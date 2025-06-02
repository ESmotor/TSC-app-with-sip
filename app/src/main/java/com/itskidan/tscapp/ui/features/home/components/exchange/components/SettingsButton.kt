package com.itskidan.tscapp.ui.features.home.components.exchange.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import com.itskidan.tscapp.R

@Composable
fun SettingsButton(
    iconSize: DpSize,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier.Companion
) {
    IconButton(
        modifier = modifier,
        onClick = onSettingsClick,
    ) {
        Icon(
            modifier = Modifier.Companion.size(iconSize),
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.home_screen_currency_settings),
        )
    }
}