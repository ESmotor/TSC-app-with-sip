package com.itskidan.domain.repository

import com.itskidan.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository  {
    fun observeCurrency(): Flow<List<Currency>>
}