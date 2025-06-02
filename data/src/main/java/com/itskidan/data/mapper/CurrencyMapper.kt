package com.itskidan.data.mapper

import com.itskidan.data.model.CurrencyDto
import com.itskidan.domain.model.Currency

fun CurrencyDto.toCurrency(): Currency {
    val flag = getFlagUrl(flagUrl)
    return Currency(
        id = id,
        currencyCode = currencyCode,
        currencyName = currencyName,
        exchangeRate = exchangeRate,
        flagUrl = flag
    )
}


fun getFlagUrl(flag: String): String {
    return "https://firebasestorage.googleapis.com/v0/b/tsc-linphonefcm.firebasestorage.app/o/flags%2F${flag.lowercase()}?alt=media"
}