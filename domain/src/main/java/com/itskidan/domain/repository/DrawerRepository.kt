package com.itskidan.domain.repository

import com.itskidan.domain.model.DrawerItem
import kotlinx.coroutines.flow.Flow

interface DrawerRepository {
    suspend fun getDrawerItems(): Flow<List<DrawerItem>>
}