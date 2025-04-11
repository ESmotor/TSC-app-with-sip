package com.itskidan.data.repository

import com.itskidan.domain.model.DrawerItem
import com.itskidan.domain.repository.DrawerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DrawerRepositoryImpl @Inject constructor() : DrawerRepository {
    override suspend fun getDrawerItems(): Flow<List<DrawerItem>> = flow {
        emit(
            listOf(
                DrawerItem(
                    labelText = "Inbox",
                    descriptionText = "Inbox mail",
                    badgeCount = 17
                ),
                DrawerItem(
                    labelText = "Outbox",
                    descriptionText = "Outbox mail",
                ),
                DrawerItem(
                    labelText = "Trash",
                    descriptionText = "Trash",
                ),
                DrawerItem(
                    labelText = "Favorites",
                    descriptionText = "Favorites",
                    badgeCount = 9
                )
            )
        )
    }

}