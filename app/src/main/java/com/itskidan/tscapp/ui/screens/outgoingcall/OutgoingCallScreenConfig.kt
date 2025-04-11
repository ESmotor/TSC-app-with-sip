package com.itskidan.tscapp.ui.screens.outgoingcall

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.itskidan.tscapp.R
import com.itskidan.tscapp.ui.theme.onSurfaceDark
import com.itskidan.tscapp.ui.theme.onSurfaceLight
import com.itskidan.tscapp.ui.theme.surfaceContainerDark
import com.itskidan.tscapp.ui.theme.surfaceContainerLight

object OutgoingCallScreenConfig {
    // Background
    val colorBackgroundScreen = Color(0xFF070A15)
    val callScreenBackgroundPattern = R.drawable.call_screen_pattern
    const val PATTERN_ALPHA = 0.3f

    // Avatar
    val avatarPlaceholder = R.drawable.default_avatar
    const val AVATAR_COMPACT_SCALE = 0.25f
    const val AVATAR_MEDIUM_SCALE = 0.15f
    const val AVATAR_EXPANDED_SCALE = 0.1f
    const val AVATAR_DEFAULT_SCALE = 0.25f
    const val AVATAR_SCALE_COEFFICIENT = 1
    val COMPACT_BREAKPOINT = 360.dp
    val AVATAR_MIN_SIZE = 120.dp
    val AVATAR_MAX_SIZE = 250.dp

    @StringRes
    val avatarDescRes = R.string.outgoing_call_screen_avatar_desc

    // Call Header
    val colorOutgoingCallName = Color(0xFFFFFFFF)
    val colorCallStatus = Color(0xFFFFFFFF)


    // Control Buttons
    val colorHangUpButton = Color(0xFFBA1A1A)
    val colorIconOn = onSurfaceLight
    val colorIconOff = onSurfaceDark
    val colorControlButtonsOff = surfaceContainerDark
    val colorControlButtonsOn = surfaceContainerLight
    val colorMessageButton = Color(0xFFFFFFFF)
    val colorContentMessageButton = Color(0xFF0D305F)
    val controlButtonSize = 48.dp
    val controlIconSize = 24.dp
    val messageButtonWidth = 200.dp

    @StringRes
    val buttonDescHangUpRes = R.string.outgoing_call_screen_hang_up_desc

    @StringRes
    val buttonDescSpeakerRes = R.string.outgoing_call_screen_speakerphone_off_on_desc

    @StringRes
    val buttonDescMicRes = R.string.outgoing_call_screen_microphone_off_on_desc

    @StringRes
    val messageButtonText = R.string.outgoing_call_screen_button_send_message
}