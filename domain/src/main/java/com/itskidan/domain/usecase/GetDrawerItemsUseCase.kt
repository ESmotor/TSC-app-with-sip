package com.itskidan.domain.usecase

import com.itskidan.domain.model.DrawerItem
import com.itskidan.domain.repository.DrawerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDrawerItemsUseCase @Inject constructor (private val repository: DrawerRepository) {
    suspend operator fun invoke(): Flow<List<DrawerItem>> {
        return repository.getDrawerItems()
    }
}