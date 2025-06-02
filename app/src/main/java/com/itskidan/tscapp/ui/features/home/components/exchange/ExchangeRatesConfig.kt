package com.itskidan.tscapp.ui.features.home.components.exchange

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Expanded
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Medium
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.itskidan.tscapp.R


object ExchangeRatesConfig {
    val PLACEHOLDER_FLAG = R.drawable.flag_default

    val Compact = ExchangeRatesCardConfig(
        flagSize = DpSize(32.dp, 32.dp),
        iconSize = DpSize(24.dp, 24.dp),
    )
    val Medium = ExchangeRatesCardConfig(
        flagSize = DpSize(40.dp, 40.dp),
        iconSize = DpSize(28.dp, 28.dp),
    )
    val Expanded = ExchangeRatesCardConfig(
        flagSize = DpSize(48.dp, 48.dp),
        iconSize = DpSize(32.dp, 32.dp),
    )
}

data class ExchangeRatesCardConfig(
    val flagSize: DpSize,
    val iconSize: DpSize,
)

fun getExchangeRatesCardConfig(windowWidthSizeClass: WindowWidthSizeClass): ExchangeRatesCardConfig {
    return when (windowWidthSizeClass) {
        Compact -> ExchangeRatesConfig.Compact
        Medium -> ExchangeRatesConfig.Medium
        Expanded -> ExchangeRatesConfig.Expanded
        else -> ExchangeRatesConfig.Compact
    }
}
