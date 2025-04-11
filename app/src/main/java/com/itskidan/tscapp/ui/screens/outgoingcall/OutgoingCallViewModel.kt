package com.itskidan.tscapp.ui.screens.outgoingcall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itskidan.domain.usecase.linphone.LinphoneHangUpCallUseCase
import com.itskidan.domain.usecase.linphone.ObserveCallSateUseCase
import com.itskidan.tscapp.ui.common.mapCallStateToUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    private val _uiState = MutableStateFlow(OutgoingCallUiState())
    val uiState: StateFlow<OutgoingCallUiState> get() = _uiState

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    private val _callDuration = MutableStateFlow(0L)
    val callDuration: StateFlow<Long> = _callDuration.asStateFlow()

    private var timerJob: Job? = null

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
                        mapCallStateToUiState(currentState, callState)
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


    // Timer
    fun startCallTimer() {
        timerJob?.cancel() // Отменяем предыдущий таймер, если был
        _callDuration.value = 0L // Сбрасываем счётчик

        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000) // Ждём 1 секунду
                _callDuration.update { it + 1 } // Увеличиваем счётчик
                updateCallDurationText() // Обновляем текст в UI
            }
        }
    }

    fun stopCallTimer() {
        timerJob?.cancel()
        timerJob = null
        _callDuration.value = 0L
    }



    fun updateCallDurationText() {
        _uiState.update { currentState ->
            currentState.copy(
                callStateText = formatDurationTimer(_callDuration.value)
            )
        }
    }

    fun formatDurationTimer(seconds: Long): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return "%02d:%02d".format(minutes, remainingSeconds) // "05:23"
    }


}







