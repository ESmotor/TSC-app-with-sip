package com.itskidan.tscapp.ui.screens.outgoingcall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itskidan.domain.usecase.linphone.LinphoneHangUpCallUseCase
import com.itskidan.domain.usecase.linphone.ObserveCallSateUseCase
import com.itskidan.tscapp.ui.common.Timer
import com.itskidan.tscapp.ui.common.mapCallStateToUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OutgoingCallViewModel @Inject constructor(
    private val observeCallSateUseCase: ObserveCallSateUseCase,
    private val linphoneHangUpCallUseCase: LinphoneHangUpCallUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CallUiState())
    val uiState: StateFlow<CallUiState> get() = _uiState

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    private val _callDuration = MutableStateFlow(0L)
    val callDuration: StateFlow<Long> = _callDuration.asStateFlow()

    private val timer = Timer(
        coroutineScope = viewModelScope,
        onTick = { duration ->
            _callDuration.value = duration
            _uiState.update { current ->
                current.copy(
                    callState = formatDuration(duration)
                )
            }
        }
    )


    init {
        subscribeToCallState()
    }


    fun clearToast() {
        _toastMessage.value = null
    }

    private fun subscribeToCallState() {
        viewModelScope.launch {
            observeCallSateUseCase.execute()
                .collect { callState ->
                    _uiState.update { currentState ->
                        mapCallStateToUiState(
                            currentState = currentState,
                            callState = callState,
                            timer = timer
                        )
                    }
                }
        }
    }

    fun toggleMute() {
        viewModelScope.launch {
            _uiState.update { it.copy(isMute = !it.isMute) }
            // Calling the VOIP library method
        }
    }

    fun toggleSpeakerphone() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSpeakerphone = !it.isSpeakerphone) }
            // Audio manager
        }
    }

    fun hungUpCall() {
        viewModelScope.launch {
            linphoneHangUpCallUseCase.invoke()
        }
    }

    fun sendMessage() {
        viewModelScope.launch {/*TODO*/ }
        _toastMessage.value = "Send Message clicked"
        // Send Message method
    }


    fun formatDuration(seconds: Long): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return "%02d:%02d".format(minutes, remainingSeconds)
    }


    fun updateCallDurationToCallState() {
        _uiState.update { currentState ->
            currentState.copy(
                callState = formatDuration(_callDuration.value)
            )
        }
    }


}







