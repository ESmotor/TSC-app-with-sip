package com.itskidan.tscapp.ui.features.call

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itskidan.domain.usecase.FcmTokenSyncUseCase
import com.itskidan.domain.usecase.linphone.AnswerCallUseCase
import com.itskidan.domain.usecase.linphone.HangUpCallUseCase
import com.itskidan.domain.usecase.linphone.ObserveStatesUseCase
import com.itskidan.tscapp.ui.features.call.mapper.mapCallStateToUiState
import com.itskidan.tscapp.ui.features.call.model.CallUiState
import com.itskidan.tscapp.ui.features.call.model.Timer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor(
    private val fcmTokenSyncUseCase: FcmTokenSyncUseCase,
    private val hangUpCallUseCase: HangUpCallUseCase,
    private val answerCallUseCase: AnswerCallUseCase,
    private val observeStatesUseCase: ObserveStatesUseCase,
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
            observeStatesUseCase.getCallState()
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



    fun fetchFcmToken() {
        viewModelScope.launch {
            try {
                fcmTokenSyncUseCase()
            } catch (e: Exception) {
                Timber.Forest.tag("MyLog").e(e, "Failed to fetch FCM token")
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
            hangUpCallUseCase.invoke()
        }
    }
    fun answerCall() {
        viewModelScope.launch {
            answerCallUseCase.invoke()
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
}