package com.itskidan.tscapp.ui.features.home.components.exchange.components

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import com.itskidan.tscapp.ui.features.home.components.exchange.model.CurrencyUi
import com.itskidan.tscapp.ui.theme.LocalPaddingValues

@Composable
fun CurrencyListRow(
    currencies: List<CurrencyUi>,
    flagSize: DpSize,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState)
    val paddingValues = LocalPaddingValues.current

    LazyRow(
        modifier = modifier,
        state = lazyListState,
        flingBehavior = flingBehavior,
        horizontalArrangement = Arrangement.spacedBy(paddingValues.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(currencies, key = { it.currencyCode }) { currency ->
            CurrencyItem(
                currency = currency,
                flagSize = flagSize,
                fontStyle = textStyle
            )
        }
        item {
            Spacer(modifier = Modifier.width(paddingValues.extraSmall))
        }
    }
}