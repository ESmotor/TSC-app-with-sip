package com.itskidan.tscapp.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itskidan.domain.usecase.ObserveCurrenciesUseCase
import com.itskidan.domain.usecase.ObserveServicesUseCase
import com.itskidan.tscapp.ui.features.home.components.exchange.mapper.toUi
import com.itskidan.tscapp.ui.features.home.components.services.mapper.toUi
import com.itskidan.tscapp.ui.features.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val observeServicesUseCase: ObserveServicesUseCase,
    private val observeCurrenciesUseCase: ObserveCurrenciesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: MutableStateFlow<HomeUiState> get() = _uiState

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    init {
        observeServices()
        observeCurrencies()
    }

    // Toast Message methods
    fun clearToast() {
        _toastMessage.value = null
    }

    fun makeToast(message: String) {
        _toastMessage.value = message
    }

    // Services section methods
    private fun observeServices() {
        viewModelScope.launch {
            observeServicesUseCase.invoke().map { list ->
                list.map { service ->
                    service.toUi()
                }
            }.collect { servicesUiList ->
                _uiState.update { it.copy(services = servicesUiList) }
            }
        }
    }

    // Currencies exchange section methods
    private fun observeCurrencies() {
        viewModelScope.launch {
            observeCurrenciesUseCase.invoke().map { list ->
                list.map { currency ->
                    currency.toUi()
                }
            }.collect { currenciesUiList ->
                _uiState.update { it.copy(currencies = currenciesUiList) }
            }
        }
    }

}