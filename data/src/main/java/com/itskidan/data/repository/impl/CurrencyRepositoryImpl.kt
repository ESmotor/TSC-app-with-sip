package com.itskidan.data.repository.impl

import com.itskidan.data.mapper.toCurrency
import com.itskidan.data.source.remote.CurrencyRemoteDataSource
import com.itskidan.domain.model.Currency
import com.itskidan.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteDataSource: CurrencyRemoteDataSource
) : CurrencyRepository {
    override fun observeCurrency(): Flow<List<Currency>> {
        return remoteDataSource.observeCurrencies().map { list -> list.map { it.toCurrency() } }
    }
}