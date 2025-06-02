package com.itskidan.tscapp.ui.features.home.components.services.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ServicesDescription(
    title: String,
    subtitle: String,
    styleTitle: TextStyle,
    styleSubtitle: TextStyle
) {
    Text(
        text = title,
        style = styleTitle,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Text(
        text = subtitle,
        style = styleSubtitle,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}