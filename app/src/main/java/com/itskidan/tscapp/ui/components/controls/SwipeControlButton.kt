package com.itskidan.tscapp.ui.components.controls

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.itskidan.tscapp.ui.screens.call.CallScreenConfig
import kotlinx.coroutines.launch
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
@Preview
fun SwipeControlButton(
    onSwipeComplete: () -> Unit = {},
    icon: ImageVector = Icons.Filled.Info,
    contentDescription: String = "",
    iconColor: Color = Color.White,
    backgroundColor: Color = Color.Green,
    dragThreshold: Dp = 50.dp,
    maxDragRadius: Dp = 200.dp,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val dragOffset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }
    var isTriggered by remember { mutableStateOf(false) }

    val density = LocalDensity.current
    val dragThresholdPx = with(density) { dragThreshold.toPx() }
    val maxDragRadiusPx = with(density) { maxDragRadius.toPx() }

    fun clampToRadius(offset: Offset, maxRadius: Float): Offset {
        val distance = offset.getDistance()
        return if (distance > maxRadius) {
            val angle = atan2(offset.y, offset.x)
            Offset(cos(angle) * maxRadius, sin(angle) * maxRadius)
        } else {
            offset
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .offset {
                IntOffset(dragOffset.value.x.roundToInt(), dragOffset.value.y.roundToInt())
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { _, dragAmount ->
                        coroutineScope.launch {
                            val targetOffset = dragOffset.value + dragAmount
                            dragOffset.snapTo(clampToRadius(targetOffset, maxDragRadiusPx))
                        }
                    },
                    onDragEnd = {
                        coroutineScope.launch {
                            val distance = dragOffset.value.getDistance()
                            if (distance >= dragThresholdPx && !isTriggered) {
                                isTriggered = true
                                onSwipeComplete()
                            }
                            dragOffset.animateTo(Offset.Zero)
                            isTriggered = false
                        }
                    }
                )
            }
            .size(CallScreenConfig.controlButtonSize)
            .background(backgroundColor, CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(CallScreenConfig.controlIconSize),
            tint = iconColor
        )
    }
}