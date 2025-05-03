package com.itskidan.tscapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.itskidan.tscapp.app.App
import com.itskidan.tscapp.navigation.NavGraph
import com.itskidan.tscapp.ui.screens.HomeFinal
import com.itskidan.tscapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.fetchFcmToken()
        // Allow content to go under system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            lifecycle.addObserver(App.instance.lifecycleObserver)
            val navController = rememberNavController()

            AppTheme {
//                NavGraph(navController)
                HomeFinal()
            }
        }
    }
}
