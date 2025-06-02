package com.itskidan.tscapp.ui.features.home.components.exchange.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import com.itskidan.tscapp.ui.features.home.components.exchange.model.CurrencyUi
import com.itskidan.tscapp.ui.theme.LocalPaddingValues

@Composable
fun CurrencyItem(
    currency: CurrencyUi,
    flagSize: DpSize,
    fontStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    val paddingValue = LocalPaddingValues.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(paddingValue.extraSmall),
    ) {
        CurrencyFlag(
            size = flagSize,
            flagUrl = currency.flagUrl,
            modifier = Modifier
        )
        CurrencyRateDisplay(
            currencyCode = currency.currencyCode,
            currencyRate = currency.exchangeRate,
            style = fontStyle,
            modifier = Modifier
        )
    }
}