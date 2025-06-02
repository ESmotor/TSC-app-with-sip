package com.itskidan.tscapp.ui.features.call

import android.app.KeyguardManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.itskidan.tscapp.app.App
import com.itskidan.tscapp.ui.theme.AppCompositionProviders
import com.itskidan.tscapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CallActivity : ComponentActivity() {

    private val viewModel: CallViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        }, 300)

        window.addFlags(
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )
        setShowWhenLocked(true)
        setTurnScreenOn(true)




        enableEdgeToEdge()
        viewModel.fetchFcmToken()

        Timber.Forest.tag("MyLog").d("[CallActivity] onCreate")

        setContent {
            lifecycle.addObserver(App.Companion.instance.lifecycleObserver)
            val windowSizeClass = calculateWindowSizeClass(this)
            AppCompositionProviders(windowSizeClass) {
                AppTheme {
                    val uiState by viewModel.uiState.collectAsState()
                    if (uiState.isCallEnded) {
                        LaunchedEffect(Unit) {
                            Timber.Forest.tag("MyLog").d("[CallActivity] is finish()")
                            finish()
                        }
                    }

                    CallScreen()
                }
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Disable closing with the back button
        moveTaskToBack(true)
    }
}