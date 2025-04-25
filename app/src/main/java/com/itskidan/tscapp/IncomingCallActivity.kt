package com.itskidan.tscapp

import android.app.KeyguardManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.itskidan.tscapp.app.App
import com.itskidan.tscapp.ui.screens.call.incomingcall.IncomingCallScreen
import com.itskidan.tscapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class IncomingCallActivity : ComponentActivity() {

    private val viewModel: IncomingCallViewModel by viewModels()

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

        Timber.tag("MyLog").d("[CallActivity] onCreate")

        setContent {
            lifecycle.addObserver(App.instance.lifecycleObserver)
            AppTheme {
                IncomingCallScreen()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Disable closing with the back button
        moveTaskToBack(true)
    }
}