package com.itskidan.tscapp.ui.features.home.components.exchange.model

data class CurrencyUi(
    val id: Int,
    val currencyCode: String,
    val currencyName: String,
    val exchangeRate: String,
    val flagUrl: String
)