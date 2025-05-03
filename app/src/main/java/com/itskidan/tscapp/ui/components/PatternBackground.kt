package com.itskidan.tscapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.imageResource

@Composable
fun Modifier.backgroundWithPattern(
    patternImage: Int,
    background: Color,
    alpha: Float
): Modifier = composed {
    val patternBrush = rememberPatternBrush(patternImage)
    this
        .background(background)
        .drawWithCache {
            onDrawBehind {
                drawRect(
                    brush = patternBrush,
                    alpha = alpha
                )
            }
        }
}

@Composable
fun rememberPatternBrush(patternImage: Int): ShaderBrush {
    val patternBitmap = ImageBitmap.imageResource(patternImage)
    return remember(patternBitmap) {
        ShaderBrush(
            ImageShader(
                image = patternBitmap,
                tileModeX = TileMode.Repeated,
                tileModeY = TileMode.Repeated
            )
        )
    }
}