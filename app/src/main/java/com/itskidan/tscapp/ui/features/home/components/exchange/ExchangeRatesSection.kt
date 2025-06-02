package com.itskidan.tscapp.ui.features.home.components.exchange

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Expanded
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Medium
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.itskidan.tscapp.R
import com.itskidan.tscapp.ui.features.home.components.exchange.components.CurrencyListRow
import com.itskidan.tscapp.ui.features.home.components.exchange.components.SettingsButton
import com.itskidan.tscapp.ui.features.home.components.exchange.model.CurrencyUi
import com.itskidan.tscapp.ui.features.home.getCurrenciesList
import com.itskidan.tscapp.ui.theme.AppCompositionProviders
import com.itskidan.tscapp.ui.theme.AppTheme
import com.itskidan.tscapp.ui.theme.LocalPaddingValues
import com.itskidan.tscapp.ui.theme.LocalWindowSizeClass

@Composable
fun ExchangeRatesSection(
    currencies: List<CurrencyUi>,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
    emptyContent: @Composable () -> Unit = { DefaultEmptyContent() },
) {
    val padding = LocalPaddingValues.current
    ExchangeRatesContent(
        currencies = currencies,
        onSettingsClick = onSettingsClick,
        emptyContent = emptyContent,
        modifier = modifier.padding(vertical = padding.small),
    )

}

@Composable
private fun ExchangeRatesContent(
    currencies: List<CurrencyUi>,
    onSettingsClick: () -> Unit,
    emptyContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val widthSizeClass = LocalWindowSizeClass.current.widthSizeClass
    val paddingValues = LocalPaddingValues.current
    val config = remember(widthSizeClass) { getExchangeRatesCardConfig(widthSizeClass) }
    val iconSize = config.iconSize
    val flagSize = config.flagSize

    val textStyle = when (widthSizeClass) {
        Compact -> MaterialTheme.typography.titleSmall
        Medium -> MaterialTheme.typography.titleMedium
        Expanded -> MaterialTheme.typography.titleLarge
        else -> MaterialTheme.typography.titleSmall
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = paddingValues.medium),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(start = paddingValues.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (currencies.isEmpty()) {
                Box(Modifier.weight(1f)) {
                    emptyContent()
                }
            } else {
                CurrencyListRow(
                    currencies = currencies,
                    flagSize = flagSize,
                    textStyle = textStyle,
                    modifier = Modifier.weight(1f)
                )
            }

            VerticalDivider(modifier = Modifier.height(iconSize.height))

            SettingsButton(
                iconSize = iconSize,
                onSettingsClick = onSettingsClick,
                modifier = Modifier
            )
        }
    }
}


@Composable
private fun DefaultEmptyContent(
    modifier: Modifier = Modifier,
) {
    val widthSizeClass = LocalWindowSizeClass.current.widthSizeClass

    val textStyle = when (widthSizeClass) {
        Compact -> MaterialTheme.typography.titleSmall
        Medium -> MaterialTheme.typography.titleMedium
        Expanded -> MaterialTheme.typography.titleLarge
        else -> MaterialTheme.typography.titleSmall
    }

    Text(
        modifier = modifier,
        text = stringResource(R.string.home_screen_exchange_no_currencies_available),
        style = textStyle
    )
}


//Preview
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CompactPreviewEmptyList() {
    val currencies = listOf<CurrencyUi>()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(411.dp, 891.dp)
    )
    PreviewExchangeRatesSection(currencies, windowSizeClass)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CompactPreview() {
    val currencies = getCurrenciesList()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(411.dp, 891.dp)
    )
    PreviewExchangeRatesSection(currencies, windowSizeClass)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun CompactPreviewDarkMode() {
    val currencies = getCurrenciesList()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(411.dp, 891.dp)
    )
    PreviewExchangeRatesSection(currencies, windowSizeClass)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
@Composable
fun MediumPreview() {
    val currencies = getCurrenciesList()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(673.dp, 841.dp)
    )
    PreviewExchangeRatesSection(currencies, windowSizeClass)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp")
@Composable
fun ExtendedPreview() {
    val currencies = getCurrenciesList()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(1280.dp, 800.dp)
    )
    PreviewExchangeRatesSection(currencies, windowSizeClass)
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp")
@Composable
fun ExtendedPreviewEmptyList() {
    val currencies = listOf<CurrencyUi>()
    val windowSizeClass = WindowSizeClass.calculateFromSize(
        size = DpSize(1280.dp, 800.dp)
    )
    PreviewExchangeRatesSection(currencies, windowSizeClass)
}

@Composable
fun PreviewExchangeRatesSection(
    currencies: List<CurrencyUi>,
    windowSizeClass: WindowSizeClass
) {
    AppCompositionProviders(windowSizeClass = windowSizeClass) {
        AppTheme {
            ExchangeRatesSection(
                currencies = currencies,
                onSettingsClick = {}
            )
        }
    }
}