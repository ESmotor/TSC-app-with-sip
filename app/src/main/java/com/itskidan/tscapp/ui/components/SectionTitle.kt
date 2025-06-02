package com.itskidan.tscapp.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String,
    style: TextStyle = MaterialTheme.typography.titleMedium
) {
    Text(
        modifier = modifier,
        text = title,
        style = style
    )
}