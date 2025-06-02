package com.itskidan.tscapp.ui.features.home.model

import com.itskidan.tscapp.ui.features.home.components.exchange.model.CurrencyUi
import com.itskidan.tscapp.ui.features.home.components.services.model.ServiceUi

data class HomeUiState(
    val services: List<ServiceUi> = listOf(),
    val currencies: List<CurrencyUi> = listOf()
)