package com.itskidan.tscapp.ui.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Timer(
    private val coroutineScope: CoroutineScope,
    private val onTick: (Long) -> Unit,
) {
    private var timerJob: Job? = null
    private var _currentDuration = 0L

    fun start() {
        stop()
        resume()
    }

    fun resume() {
        timerJob = coroutineScope.launch {
            while (true) {
                delay(1000)
                _currentDuration++
                onTick(_currentDuration)
            }
        }
    }

    fun pause() {
        timerJob?.cancel()
        timerJob = null
    }

    fun stop() {
        pause()
        _currentDuration = 0L
    }

}