package com.itskidan.tscapp.ui.features.home.components.exchange.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun CurrencyRateDisplay(
    currencyCode: String,
    currencyRate: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleSmall,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = currencyRate,
            style = style,
            color = textColor,
        )
        Text(
            text = currencyCode,
            style = style,
            color = textColor,
        )
    }
}
