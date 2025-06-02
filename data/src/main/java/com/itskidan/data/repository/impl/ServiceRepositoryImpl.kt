package com.itskidan.data.repository.impl

import com.itskidan.data.mapper.toService
import com.itskidan.data.source.remote.ServiceRemoteDataSource
import com.itskidan.domain.model.Service
import com.itskidan.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(
    private val remoteDataSource: ServiceRemoteDataSource
) : ServiceRepository {
    override fun observeServices(): Flow<List<Service>> {
        return remoteDataSource.observeServices().map { list -> list.map { it.toService() } }
    }
}
