package com.itskidan.tscapp.ui.features.home.components.exchange.mapper

import com.itskidan.domain.model.Currency
import com.itskidan.tscapp.ui.features.home.components.exchange.model.CurrencyUi

fun Currency.toUi(): CurrencyUi {
    return CurrencyUi(
        id = id,
        currencyCode = currencyCode,
        currencyName = currencyName,
        exchangeRate = exchangeRate.toString(),
        flagUrl = flagUrl
    )
}