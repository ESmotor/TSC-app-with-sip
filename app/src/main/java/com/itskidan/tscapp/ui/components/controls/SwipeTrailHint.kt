package com.itskidan.tscapp.ui.components.controls

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.itskidan.tscapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
@Preview
fun SwipeTrailHint(
    arrowCount : Int = 4,
    startDelay : Long = 100L,
    animationCycle : Int = 1000,
    alphaFrom : Float = 0f,
    alphaTo : Float = 1f,
    betweenArrows : Dp = 8.dp,
    arrowSize : Dp = 24.dp,
    arrowColor : Color = Color.White,
    description: String = stringResource(R.string.call_screen_swipe_up_indicator),
    modifier: Modifier = Modifier
) {
    val alphas = remember (arrowCount) { List(arrowCount) { Animatable(alphaFrom) } }

    LaunchedEffect(Unit) {
        alphas.reversed().forEachIndexed { index, anim ->
            delay(index * startDelay)
            launch {
                anim.animateTo(
                    targetValue = alphaTo,
                    animationSpec = infiniteRepeatable(
                        animation = tween(animationCycle),
                        repeatMode = RepeatMode.Reverse
                    )
                )
            }
        }
    }

    Column(
        modifier = modifier
            .padding(betweenArrows),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        alphas.forEach { anim ->
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = description,
                tint = arrowColor,
                modifier = Modifier
                    .size(arrowSize)
                    .alpha(anim.value)
            )
        }
    }
}