package com.itskidan.domain.model

sealed class CoreState {
    object Off : CoreState()
    object Startup : CoreState()
    object On : CoreState()
    object Shutdown : CoreState()
    object Configuring : CoreState()
    object Ready : CoreState()
    object Unknown : CoreState()
}