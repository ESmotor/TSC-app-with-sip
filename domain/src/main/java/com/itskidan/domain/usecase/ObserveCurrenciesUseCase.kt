package com.itskidan.domain.usecase

import com.itskidan.domain.model.Currency
import com.itskidan.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(): Flow<List<Currency>> = repository.observeCurrency()
}