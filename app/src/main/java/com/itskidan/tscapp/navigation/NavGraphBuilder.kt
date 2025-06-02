package com.itskidan.tscapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itskidan.tscapp.ui.features.home.HomeScreen

object NavConst {
    // for Navigation
    const val HOME = "home_screen"

}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavConst.HOME
    ) {
        composable(NavConst.HOME) {
            HomeScreen(
                navController = navController
            )
        }
    }
}