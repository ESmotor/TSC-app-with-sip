package com.itskidan.domain.usecase.linphone

import com.itskidan.domain.model.linphone.LinphoneCoreState
import com.itskidan.domain.repository.linphone.LinphoneCoreStatusObserver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCoreStateUseCase @Inject constructor(
    private val linphoneCoreStatusObserver: LinphoneCoreStatusObserver
) {
    fun execute(): Flow<LinphoneCoreState> = linphoneCoreStatusObserver.coreState
}