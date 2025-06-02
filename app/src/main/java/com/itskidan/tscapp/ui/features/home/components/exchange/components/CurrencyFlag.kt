package com.itskidan.tscapp.ui.features.home.components.exchange.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.itskidan.tscapp.R
import com.itskidan.tscapp.ui.features.home.components.exchange.ExchangeRatesConfig

@Composable
fun CurrencyFlag(
    size: DpSize,
    modifier: Modifier = Modifier,
    flagUrl: String = "default",
) {
    Surface(
        modifier = modifier
            .clip(CircleShape)
            .size(size),
        color = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 1.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = flagUrl,
                contentDescription = stringResource(R.string.home_screen_currency_flag),
                modifier = Modifier
                    .size(size)
                    .clip(CircleShape),
                placeholder = painterResource(ExchangeRatesConfig.PLACEHOLDER_FLAG),
                error = painterResource(ExchangeRatesConfig.PLACEHOLDER_FLAG),
                contentScale = ContentScale.Fit
            )
        }
    }
}