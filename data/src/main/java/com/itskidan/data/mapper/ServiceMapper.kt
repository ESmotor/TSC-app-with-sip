package com.itskidan.data.mapper

import com.itskidan.data.model.ServiceDto
import com.itskidan.domain.model.Service

fun ServiceDto.toService(): Service {
    return Service(
        id = id,
        title = title,
        description = description,
        iconName = iconName
    )
}