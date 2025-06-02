package com.itskidan.tscapp.ui.features.home.components.emergency

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Medium
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Adaptive Emergency Button Component
 *
 * Automatically adjusts size and layout based on window size class:
 * - Compact (mobile phones in portrait)
 * - Medium (tablets in portrait/large phones)
 * - Expanded (tablets in landscape)
 */
@Composable
fun EmergencyButton(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit = {},
    enabled: Boolean = true,
    indicatorColor: Color = Color(0xFFBA1A1A),
    longPressDuration: Int = 400,
) {
    // Determine the dimensions depending on the screen class
    val dimensions = remember(windowSizeClass.widthSizeClass) {
        when (windowSizeClass.widthSizeClass) {
            Compact -> EmergencyButtonDimensions.compact()
            Medium -> EmergencyButtonDimensions.medium()
            else -> EmergencyButtonDimensions.expanded()
        }
    }

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    // Animations
    val elevation by animateDpAsState(
        if (pressed) 1.dp else dimensions.elevation,
        animationSpec = tween(durationMillis = longPressDuration.coerceAtLeast(1)),
        label = "buttonElevation"
    )
    val scale by animateFloatAsState(
        if (pressed) 0.85f else 1f,
        animationSpec = tween(durationMillis = longPressDuration.coerceAtLeast(1)),
        label = "buttonScale"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensions.containerHeight)
            .padding(dimensions.containerPadding),
        contentAlignment = Alignment.Center
    ) {
        // Glow effect of active button
        if (enabled) {
            GlowEffect(indicatorColor, dimensions)
        }

        // Main button
        Surface(
            modifier = Modifier
                .size(dimensions.buttonSize)
                .scale(scale),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainerHighest,
            shadowElevation = elevation,
            tonalElevation = 2.dp
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(enabled, longPressDuration) {
                        if (!enabled) return@pointerInput

                        detectTapGestures(
                            onPress = { offset ->
                                val startTime = System.currentTimeMillis()

                                val press = PressInteraction.Press(offset)
                                interactionSource.emit(press)

                                val isLongPress = try {
                                    tryAwaitRelease()
                                    System.currentTimeMillis() - startTime >= longPressDuration
                                } catch (e: Exception) {
                                    false
                                } finally {
                                    interactionSource.emit(PressInteraction.Release(press))
                                }

                                onClick(isLongPress)
                            }
                        )
                    }
                    .indication(
                        interactionSource = interactionSource,
                        indication = ripple(
                            bounded = true,
                            radius = dimensions.rippleRadius,
                            color = Color.White
                        )
                    )
            ) {
                // Red indicator
                ColorIndicator(indicatorColor, dimensions, enabled)

                // Inner surface
                InnerContent(pressed, enabled, windowSizeClass, dimensions)
            }
        }
    }
}

@Composable
private fun GlowEffect(
    indicatorColor: Color,
    dimensions: EmergencyButtonDimensions
) {
    Box(
        modifier = Modifier
            .size(dimensions.glowSize)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        indicatorColor.copy(alpha = 0.2f),
                        Color.Transparent
                    ),
                    radius = dimensions.glowRadius
                ),
                shape = CircleShape
            )
    )
}

@Composable
private fun ColorIndicator(
    indicatorColor: Color,
    dimensions: EmergencyButtonDimensions,
    enabled: Boolean
) {
    Box(
        modifier = Modifier
            .size(dimensions.indicatorSize)
            .background(
                color = if (enabled) indicatorColor
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                shape = CircleShape
            )
    )
}

@Composable
private fun InnerContent(
    pressed: Boolean,
    enabled: Boolean,
    windowSizeClass: WindowSizeClass,
    dimensions: EmergencyButtonDimensions
) {
    Surface(
        modifier = Modifier.size(dimensions.innerSize),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surfaceContainer,
        shadowElevation = if (pressed) 1.dp else dimensions.innerElevation
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "SOS",
                style = when (windowSizeClass.widthSizeClass) {
                    Compact -> MaterialTheme.typography.headlineSmall
                    Medium -> MaterialTheme.typography.headlineMedium
                    else -> MaterialTheme.typography.headlineLarge
                },
                color = if (enabled) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CompactPreview() {
    MaterialTheme {
        EmergencyButton(
            windowSizeClass = WindowSizeClass.calculateFromSize(
                size = DpSize(411.dp, 891.dp)
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
fun MediumPreview() {
    MaterialTheme {
        EmergencyButton(
            windowSizeClass = WindowSizeClass.calculateFromSize(
                size = DpSize(673.dp, 841.dp)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp")
@Composable
fun ExpandedPreview() {
    MaterialTheme {
        EmergencyButton(
            windowSizeClass = WindowSizeClass.calculateFromSize(
                size = DpSize(1280.dp, 800.dp)
            )
        )
    }
}