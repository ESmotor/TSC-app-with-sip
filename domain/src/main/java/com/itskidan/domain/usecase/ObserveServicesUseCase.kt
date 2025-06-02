package com.itskidan.domain.usecase

import com.itskidan.domain.model.Service
import com.itskidan.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveServicesUseCase @Inject constructor(
    private val repository: ServiceRepository
) {
    operator fun invoke(): Flow<List<Service>> = repository.observeServices()
}