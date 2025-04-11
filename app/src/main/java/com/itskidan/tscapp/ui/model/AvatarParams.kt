package com.itskidan.tscapp.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

data class AvatarParams(
    val image: Any?,
    val scale: Float,
    val minSize: Dp,
    val maxSize: Dp,
    val compactBreakpoint: Dp,
    val description: String,
    @DrawableRes val placeholder: Int,
    val contentScale: ContentScale = ContentScale.Crop
)