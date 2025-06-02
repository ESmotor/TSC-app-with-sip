package com.itskidan.data.source.remote

import com.itskidan.data.model.CurrencyDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRemoteDataSource @Inject constructor() {
    fun observeCurrencies(): Flow<List<CurrencyDto>>  = flow {
        emit(
            listOf(
                CurrencyDto(
                    id = 1,
                    currencyCode = "USD",
                    currencyName = "US Dollar",
                    exchangeRate = 1377.66,
                    flagUrl = "usd.webp"
                ),
                CurrencyDto(
                    id = 2,
                    currencyCode = "EUR",
                    currencyName = "Euro",
                    exchangeRate = 2365.87,
                    flagUrl = "eur.webp"
                ),
                CurrencyDto(
                    id = 3,
                    currencyCode = "RUB",
                    currencyName = "Russian Ruble",
                    exchangeRate = 8965.47,
                    flagUrl = "rub.webp"
                ),
                CurrencyDto(
                    id = 4,
                    currencyCode = "KZT",
                    currencyName = "Kazakhstani Tenge",
                    exchangeRate = 2796.54,
                    flagUrl = "kzt.webp"
                ),
                CurrencyDto(
                    id = 5,
                    currencyCode = "CAD",
                    currencyName = "Canadian Dollar",
                    exchangeRate = 5596.54,
                    flagUrl = "cad.webp"
                ),
                CurrencyDto(
                    id = 6,
                    currencyCode = "JPY",
                    currencyName = "Japanese Yen",
                    exchangeRate = 3696.54,
                    flagUrl = "jpy.webp"
                ),
            )
        )
    }
}