package com.itskidan.tscapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itskidan.domain.usecase.FcmTokenSyncUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fcmTokenSyncUseCase: FcmTokenSyncUseCase
): ViewModel()  {
    fun fetchFcmToken() {
        viewModelScope.launch {
            try {
               fcmTokenSyncUseCase()
            } catch (e: Exception) {
                Timber.tag("MyLog").e(e, "Failed to fetch FCM token")
            }
        }
    }
}