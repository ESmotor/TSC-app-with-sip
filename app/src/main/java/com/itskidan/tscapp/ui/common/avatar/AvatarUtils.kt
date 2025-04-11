package com.itskidan.tscapp.ui.common.avatar

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.itskidan.tscapp.ui.model.AvatarParams
import com.itskidan.tscapp.ui.screens.outgoingcall.OutgoingCallScreenConfig
import com.itskidan.tscapp.ui.screens.outgoingcall.OutgoingCallScreenConfig.AVATAR_COMPACT_SCALE
import com.itskidan.tscapp.ui.screens.outgoingcall.OutgoingCallScreenConfig.AVATAR_DEFAULT_SCALE
import com.itskidan.tscapp.ui.screens.outgoingcall.OutgoingCallScreenConfig.AVATAR_EXPANDED_SCALE
import com.itskidan.tscapp.ui.screens.outgoingcall.OutgoingCallScreenConfig.AVATAR_MEDIUM_SCALE
import com.itskidan.tscapp.ui.screens.outgoingcall.OutgoingCallScreenConfig.AVATAR_SCALE_COEFFICIENT

fun getAvatarParams(
    windowSizeClass: WindowWidthSizeClass,
    avatarImage: Any?
): AvatarParams {
    return AvatarParams(
        image = avatarImage,
        scale = getAvatarScale(windowSizeClass),
        minSize = OutgoingCallScreenConfig.AVATAR_MIN_SIZE,
        maxSize = OutgoingCallScreenConfig.AVATAR_MAX_SIZE,
        compactBreakpoint = OutgoingCallScreenConfig.COMPACT_BREAKPOINT,
        description = OutgoingCallScreenConfig.avatarDescRes.toString(),
        placeholder = OutgoingCallScreenConfig.avatarPlaceholder
    )
}

private fun getAvatarScale(
    windowSizeClass: WindowWidthSizeClass,
    coefficient: Int = AVATAR_SCALE_COEFFICIENT
): Float {
    return when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> AVATAR_COMPACT_SCALE * coefficient
        WindowWidthSizeClass.Medium -> AVATAR_MEDIUM_SCALE * coefficient
        WindowWidthSizeClass.Expanded -> AVATAR_EXPANDED_SCALE * coefficient
        else -> AVATAR_DEFAULT_SCALE
    }
}