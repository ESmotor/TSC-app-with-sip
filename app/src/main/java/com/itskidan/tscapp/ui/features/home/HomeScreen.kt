package com.itskidan.tscapp.ui.features.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.itskidan.tscapp.R
import com.itskidan.tscapp.ui.components.ContactCenterFab
import com.itskidan.tscapp.ui.components.navigation.BottomNavigation
import com.itskidan.tscapp.ui.features.home.components.exchange.model.CurrencyUi
import com.itskidan.tscapp.ui.features.home.components.news.NewsUi
import com.itskidan.tscapp.ui.features.home.components.services.model.ServiceUi
import com.itskidan.tscapp.ui.features.home.components.exchange.ExchangeRatesSection
import com.itskidan.tscapp.ui.features.home.components.news.NewsSection
import com.itskidan.tscapp.ui.features.home.components.services.ServicesSection
import com.itskidan.tscapp.ui.features.home.components.emergency.EmergencyButton
import com.itskidan.tscapp.ui.features.home.components.greeting.GreetingSection
import com.itskidan.tscapp.ui.features.home.model.HomeUiState
import com.itskidan.tscapp.ui.theme.AppCompositionProviders
import com.itskidan.tscapp.ui.theme.AppTheme
import com.itskidan.tscapp.ui.theme.LocalPaddingValues
import com.itskidan.tscapp.ui.theme.LocalWindowSizeClass

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val windowSizeClass = LocalWindowSizeClass.current
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Show simple ToastMessage
    val toastMessage by viewModel.toastMessage.collectAsStateWithLifecycle()
    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearToast()
        }
    }


    Scaffold(
        topBar = { },
        bottomBar = {
            BottomNavigation(onItemSelected = { bottomItem ->
                viewModel.makeToast("Bottom item: ${bottomItem.label} clicked")
            })
        },
        floatingActionButton = {
            ContactCenterFab(onClick = { viewModel.makeToast("FAB clicked") })
        }
    ) { paddingValues ->
        HomeScreenContent(
            modifier = Modifier.padding(paddingValues),
            windowSizeClass = windowSizeClass,
            uiState = uiState,
            onNotificationClick = { viewModel.makeToast("Notification clicked") },
            onCurrencySettingsClick = { viewModel.makeToast("Currency settings clicked") },
            onServiceClick = { service -> viewModel.makeToast("Service ${service.title} clicked") },
            onEmergencyButtonClick = { isLongClick ->
                if (isLongClick) {
                    viewModel.makeToast("Emergency long clicked")
                } else {
                    viewModel.makeToast("Emergency short clicked")
                }
            }
        )
    }

}


@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
    uiState: HomeUiState,
    onNotificationClick: () -> Unit,
    onEmergencyButtonClick: (Boolean) -> Unit,
    onCurrencySettingsClick: () -> Unit,
    onServiceClick: (ServiceUi) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            GreetingSection(
                userName = "Valera Smirnov",
                date = "May 2, 2025",
                onNotificationClick = onNotificationClick
            )
        }

        item {
            EmergencyButton(
                windowSizeClass = windowSizeClass,
                onClick = onEmergencyButtonClick
            )
        }

        item {
            ExchangeRatesSection(
                currencies = uiState.currencies,
                onSettingsClick = onCurrencySettingsClick
            )
        }

        item {
            Spacer(modifier = Modifier.height(LocalPaddingValues.current.small))
        }
        item {
            ServicesSection(
                services = uiState.services,
                onServiceClick = onServiceClick,
                modifier = Modifier
            )
        }

        item {
            Spacer(modifier = Modifier.height(LocalPaddingValues.current.medium))
        }

        item {
            NewsSection(
                newsList = listOf(
                    NewsUi(
                        R.drawable.default_news,
                        "London is the capital of Great Britain",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                        "02.05.2025"
                    ),
                    NewsUi(R.drawable.default_news, "Title 2", "Description 2", "03.06.2025"),
                    NewsUi(R.drawable.default_news, "Title 3", "Description 3", "04.07.2025"),
                    NewsUi(R.drawable.default_news, "Title 4", "Description 4", "12.08.2025"),
                    NewsUi(R.drawable.default_news, "Title 5", "Description 5", "22.09.2025"),
                    NewsUi(R.drawable.default_news, "Title 6", "Description 6", "10.04.2025")
                )
            )
        }

    }
}





// Preview section
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun HomeFinalPreviewLightCompact() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(411.dp, 891.dp))
    AppCompositionProviders(windowSizeClass = windowSizeClass) {
        AppTheme {
            HomeScreenPreview(windowSizeClass)
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=411dp,height=891dp"
)
fun HomeFinalPreviewDarkCompact() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(411.dp, 891.dp))
    AppCompositionProviders(windowSizeClass = windowSizeClass) {
        AppTheme {
            HomeScreenPreview(windowSizeClass)
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun HomeFinalPreviewLightMedium() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(673.dp, 841.dp))
    AppCompositionProviders(windowSizeClass = windowSizeClass) {
        AppTheme {
            HomeScreenPreview(windowSizeClass)
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp")
fun HomeFinalPreviewLightExtended() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(1280.dp, 800.dp))
    AppCompositionProviders(windowSizeClass = windowSizeClass) {
        AppTheme {
            HomeScreenPreview(windowSizeClass)
        }
    }
}


@Composable
fun HomeScreenPreview(windowSizeClass: WindowSizeClass) {
    val services = getServicesList()

    val currencies = getCurrenciesList()

    val uiState = HomeUiState(
        services = services,
        currencies = currencies
    )

    Scaffold(
        topBar = { },
        bottomBar = {
            BottomNavigation(onItemSelected = {})
        },
        floatingActionButton = {
            ContactCenterFab(onClick = {})
        }
    ) { paddingValues ->
        HomeScreenContent(
            modifier = Modifier.padding(paddingValues),
            windowSizeClass = windowSizeClass,
            uiState = uiState,
            onCurrencySettingsClick = {},
            onServiceClick = {},
            onEmergencyButtonClick = {},
            onNotificationClick = {}
        )
    }
}


// function for preview
fun getCurrenciesList(): List<CurrencyUi> {
    return listOf(
        CurrencyUi(
            id = 1,
            currencyCode = "USD",
            currencyName = "US Dollar",
            exchangeRate = "1377.66",
            flagUrl = "default"
        ),
        CurrencyUi(
            id = 2,
            currencyCode = "EUR",
            currencyName = "Euro",
            exchangeRate = "2365.87",
            flagUrl = "default"
        ),
        CurrencyUi(
            id = 3,
            currencyCode = "RUB",
            currencyName = "Russian Ruble",
            exchangeRate = "8965.47",
            flagUrl = "default"
        ),
        CurrencyUi(
            id = 4,
            currencyCode = "KZT",
            currencyName = "Kazakhstani Tenge",
            exchangeRate = "2796.54",
            flagUrl = "default"
        ),
        CurrencyUi(
            id = 5,
            currencyCode = "CAD",
            currencyName = "Canadian Dollar",
            exchangeRate = "5596.54",
            flagUrl = "default"
        ),
        CurrencyUi(
            id = 6,
            currencyCode = "JPY",
            currencyName = "Japanese Yen",
            exchangeRate = "3696.54",
            flagUrl = "default"
        ),
    )

}

fun getServicesList(): List<ServiceUi> {
    return List(10) { i ->
        ServiceUi(
            id = i + 1,
            title = "Emergency",
            description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer",
            icon = Icons.Default.Notifications
        )
    }
}