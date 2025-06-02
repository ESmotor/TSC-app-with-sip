package com.itskidan.domain.repository

import com.itskidan.domain.model.Service
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {
    fun observeServices(): Flow<List<Service>>
}