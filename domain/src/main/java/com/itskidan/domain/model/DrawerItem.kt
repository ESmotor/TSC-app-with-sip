package com.itskidan.domain.model

data class DrawerItem(
    val labelText: String,
    val descriptionText: String,
    val badgeCount: Int? = null
)