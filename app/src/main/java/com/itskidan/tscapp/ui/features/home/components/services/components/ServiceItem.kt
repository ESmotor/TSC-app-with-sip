package com.itskidan.tscapp.ui.features.home.components.services.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.itskidan.tscapp.ui.features.home.components.services.model.ServiceUi
import com.itskidan.tscapp.ui.theme.AppCompositionProviders
import com.itskidan.tscapp.ui.theme.LocalPaddingValues
import com.itskidan.tscapp.ui.theme.PaddingValues

@Composable
fun ServiceItem(
    service: ServiceUi,
    width: Dp,
    height: Dp,
    iconSize: DpSize,
    titleTextStyle: TextStyle,
    subtitleTextStyle: TextStyle,
    onClick: (ServiceUi) -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    Card(
        modifier = modifier
            .width(width)
            .height(height)
            .focusable(),
        onClick = { onClick(service) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp,
            pressedElevation = 6.dp,
            focusedElevation = 4.dp,
            hoveredElevation = 3.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        interactionSource = remember { MutableInteractionSource() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues.medium),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ServiceIcon(
                icon = service.icon,
                iconSize = iconSize,
                description = service.title
            )

            Spacer(modifier = Modifier.height(paddingValues.extraSmall))

            ServicesDescription(
                title = service.title,
                styleTitle = titleTextStyle,
                subtitle = service.description,
                styleSubtitle = subtitleTextStyle
            )
        }
    }
}



@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun ServiceCardPreview() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(411.dp, 891.dp))
    AppCompositionProviders(windowSizeClass = windowSizeClass) {
        ServiceItem(
            service = ServiceUi(
                id = 777,
                title = "Ambulance",
                description = "You can call an ambulance here and they come as soon as possible",
                icon = Icons.Default.Notifications
            ),
            width = 130.dp,
            height = 130.dp,
            iconSize = DpSize(24.dp, 24.dp),
            onClick = {},
            titleTextStyle = MaterialTheme.typography.titleSmall,
            subtitleTextStyle = MaterialTheme.typography.bodySmall,
            paddingValues = LocalPaddingValues.current
        )
    }
}
