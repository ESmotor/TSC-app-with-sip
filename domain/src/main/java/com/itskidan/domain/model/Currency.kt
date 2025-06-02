package com.itskidan.domain.model

data class Currency(
    val id: Int,
    val currencyCode: String,
    val currencyName: String,
    val exchangeRate: Double,
    val flagUrl: String
)