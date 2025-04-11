package com.itskidan.domain.repository.linphone

import com.itskidan.domain.model.linphone.LinphoneCoreState
import kotlinx.coroutines.flow.Flow

interface LinphoneCoreStatusObserver {
    val coreState: Flow<LinphoneCoreState>
}