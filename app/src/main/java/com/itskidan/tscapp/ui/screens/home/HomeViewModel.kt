package com.itskidan.tscapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itskidan.data.config.LinphoneConfig
import com.itskidan.domain.model.DrawerItem
import com.itskidan.domain.repository.FcmRepository
import com.itskidan.domain.usecase.GetDrawerItemsUseCase
import com.itskidan.domain.usecase.linphone.LinphoneMakeCallUseCase
import com.itskidan.domain.usecase.linphone.LinphoneRegAccountUseCase
import com.itskidan.domain.usecase.linphone.ObserveStatesUseCase
import com.itskidan.tscapp.navigation.BottomNavItem
import com.itskidan.tscapp.ui.common.mapCoreStateToText
import com.itskidan.tscapp.ui.common.mapRegistrationStateToText
import com.itskidan.tscapp.ui.common.mapSysCallStateToText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.linphone.core.Core
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDrawerItemsUseCase: GetDrawerItemsUseCase,
    private val linphoneMakeCallUseCase: LinphoneMakeCallUseCase,
    private val linphoneRegAccountUseCase: LinphoneRegAccountUseCase,
    private val observeStatesUseCase: ObserveStatesUseCase,
    private val core: Core

) : ViewModel() {

    @Inject
    lateinit var fcmRepository: FcmRepository

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: MutableStateFlow<HomeUiState> get() = _uiState

    private val _drawerItems = MutableStateFlow<List<DrawerItem>>(emptyList())
    val drawerItems: StateFlow<List<DrawerItem>> = _drawerItems

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage


    init {
        subscribeToCallState()
        subscribeToCoreState()
        subscribeToRegState()
        loadDrawerMenu()

    }

    // Drawer Menu
    private fun loadDrawerMenu() {
        viewModelScope.launch {
            getDrawerItemsUseCase().collect { items ->
                _drawerItems.value = items
            }
        }
    }

    // DrawerMenu
    fun onDrawerSelected(item: DrawerItem) {
        val message = item.labelText
        _toastMessage.value = message
    }


    // BottomNavMenu
    fun onBottomNavSelected(item: BottomNavItem) {
        val message = when (item) {
            is BottomNavItem.Home -> "Main Selected"
            is BottomNavItem.Settings -> "Settings Selected"
        }
        _toastMessage.value = message
    }


    fun clearToast() {
        _toastMessage.value = null
    }

    /////////////////////////////////////////////////////////////////
    private suspend fun registerSIPAccount() {
        linphoneRegAccountUseCase.invoke(
            LinphoneConfig.USERNAME,
            LinphoneConfig.DOMAIN,
            LinphoneConfig.PASSWORD,
            LinphoneConfig.TRANSPORT_TYPE_TLS
        )
    }

    fun onMakeCallClicked() {
        viewModelScope.launch {
            linphoneMakeCallUseCase.invoke(phoneNumber = "111")
        }
    }

    private fun subscribeToCallState() {
        viewModelScope.launch {
            observeStatesUseCase.getCallState()
                .map { mapSysCallStateToText(it) }
                .onEach { newState ->
                    _uiState.update { it.copy(callStateText = newState) }
                }.collect()
        }
    }

    private fun subscribeToCoreState() {
        viewModelScope.launch {
            observeStatesUseCase.getCoreState()
                .map { mapCoreStateToText(it) }
                .onEach { newState ->
                    _uiState.update { it.copy(coreStateText = newState) }
                }.collect()
        }
    }

    private fun subscribeToRegState() {
        viewModelScope.launch {
            observeStatesUseCase.getRegState()
                .map { mapRegistrationStateToText(it) }
                .onEach { newState ->
                    _uiState.update { it.copy(regStateText = newState) }
                }.collect()
        }
    }

    fun onActivateService() {
        viewModelScope.launch {
            val token = fcmRepository.getFcmToken()
            Timber.tag("MyLog").d("[onActivateServiceClicked] FcmToken: $token")
            registerSIPAccount()
        }
    }
    fun onInfoClick() {
        viewModelScope.launch {
            val accountList = core.accountList
            accountList.forEach {account->
                val params = account.params
                val identity = params.identity
                Timber.tag("MyLog").d("[onInfoClick] account $identity")
            }
        }
    }


}