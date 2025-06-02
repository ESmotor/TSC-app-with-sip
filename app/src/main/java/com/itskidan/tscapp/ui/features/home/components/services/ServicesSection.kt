package com.itskidan.tscapp.ui.features.home.components.services

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.itskidan.tscapp.R
import com.itskidan.tscapp.ui.features.home.components.services.components.ServiceItem
import com.itskidan.tscapp.ui.features.home.components.services.model.ServiceUi
import com.itskidan.tscapp.ui.features.home.components.services.styles.getServiceTextStyles
import com.itskidan.tscapp.ui.features.home.getServicesList
import com.itskidan.tscapp.ui.theme.AppCompositionProviders
import com.itskidan.tscapp.ui.theme.AppTheme
import com.itskidan.tscapp.ui.theme.LocalPaddingValues
import com.itskidan.tscapp.ui.theme.LocalWindowSizeClass
import com.itskidan.tscapp.ui.theme.PaddingValues

/**
 * Responsive Design Service Grid Component
 *
 * @param services List of services to display
 * @param modifier Modifier for customizing layout
 * @param emptyContent Content when the list of services is empty
 * @param onServiceClick Action when pressing a button of any service.
 */


@Composable
fun ServicesSection(
    services: List<ServiceUi>,
    onServiceClick: (ServiceUi) -> Unit,
    modifier: Modifier = Modifier,
    emptyContent: @Composable () -> Unit = { DefaultEmptyContent() }
) {
    Column(modifier = modifier) {
        if (services.isEmpty()) {
            emptyContent()
        } else {
            ServicesContent(
                services = services,
                onServiceClick = onServiceClick,
            )
        }
    }
}

@Composable
private fun ServicesContent(
    services: List<ServiceUi>,
    onServiceClick: (ServiceUi) -> Unit,
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass = LocalWindowSizeClass.current.widthSizeClass,
    paddingValues: PaddingValues = LocalPaddingValues.current
) {
    val config = remember(widthSizeClass) { getServicesConfig(widthSizeClass) }
    val styles = getServiceTextStyles(widthSizeClass)
    val cardWidth = config.cardWidth
    val cardHeight = config.cardHeight
    val rowsNumber = config.rowsNumber
    val spacingBetweenCards = config.spaceBetween
    val iconSize = config.iconSize
    val gridHeight = config.gridHeight


    // The grid scrolls horizontally, the cards are in a row vertically
    LazyHorizontalGrid(
        rows = GridCells.Fixed(rowsNumber),
        horizontalArrangement = Arrangement.spacedBy(spacingBetweenCards),
        verticalArrangement = Arrangement.spacedBy(spacingBetweenCards),
        userScrollEnabled = true,
        modifier = modifier
            .padding(horizontal = paddingValues.medium)
            .height(gridHeight)
    ) {
        items(services, key = { it.id }) { service ->
            ServiceItem(
                service = service,
                width = cardWidth,
                height = cardHeight,
                iconSize = iconSize,
                titleTextStyle = styles.title,
                subtitleTextStyle = styles.subtitle,
                onClick = onServiceClick,
                paddingValues = paddingValues
            )
        }
    }
}



@Composable
private fun DefaultEmptyContent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalPaddingValues.current.medium)
    ) {
        Text(
            text = stringResource(R.string.home_screen_services_no_services_available),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

//Preview
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CompactPreview() {
    val servicesList = getServicesList()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(411.dp, 891.dp)
    )
    PreviewServicesSection(servicesList, windowSizeClass)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CompactPreviewEmptyList() {
    val servicesList = listOf<ServiceUi>()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(411.dp, 891.dp)
    )
    PreviewServicesSection(servicesList, windowSizeClass)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun CompactPreviewDark() {
    val servicesList = getServicesList()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(411.dp, 891.dp)
    )
    PreviewServicesSection(servicesList, windowSizeClass)
}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
fun MediumPreview() {
    val servicesList = getServicesList()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(673.dp, 841.dp)
    )
    PreviewServicesSection(servicesList, windowSizeClass)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=1200dp,height=800dp")
@Composable
fun ExpandedPreview() {
    val servicesList = getServicesList()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(1200.dp, 800.dp)
    )
    PreviewServicesSection(servicesList, windowSizeClass)
}

@Composable
fun PreviewServicesSection(
    services: List<ServiceUi>,
    windowSizeClass: WindowSizeClass
) {
    AppCompositionProviders(windowSizeClass = windowSizeClass) {
        AppTheme {
            ServicesSection(
                services = services,
                onServiceClick = {}
            )
        }
    }
}