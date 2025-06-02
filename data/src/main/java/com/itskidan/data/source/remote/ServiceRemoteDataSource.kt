package com.itskidan.data.source.remote

import com.itskidan.data.model.ServiceDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ServiceRemoteDataSource @Inject constructor() {
    fun observeServices(): Flow<List<ServiceDto>>  = flow {
     emit(
         listOf(
             ServiceDto(
                 id = 1,
                 title = "Emergency",
                 description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer",
                 iconName = "ic_notifications"
             ),
             ServiceDto(
                 id = 2,
                 title = "Police",
                 description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer",
                 iconName = "ic_home"
             ),
             ServiceDto(
                 id = 3,
                 title = "Fire",
                 description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer",
                 iconName = "ic_timer"
             ),
             ServiceDto(
                 id = 4,
                 title = "Ambulance",
                 description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer",
                 iconName = "ic_speaker"
             ),
             ServiceDto(
                 id = 5,
                 title = "Valera",
                 description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer",
                 iconName = "ic_outbox"
             ),
             ServiceDto(
                 id = 6,
                 title = "Smirnov",
                 description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer",
                 iconName = "ic_delete"
             ),
             ServiceDto(
                 id = 7,
                 title = "Ivanov",
                 description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer",
                 iconName = "ic_accountBalance"
             )
         )
     )
    }
}