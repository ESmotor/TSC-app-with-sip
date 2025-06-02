package com.itskidan.data.model

data class CurrencyDto(
    val id: Int = 0,
    val currencyCode: String = "UKN",
    val currencyName: String = "UnknownCurrency",
    val exchangeRate: Double = 7777.77,
    val flagUrl: String = "Unknown"
)