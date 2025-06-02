package com.itskidan.tscapp.ui.common.avatar

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.itskidan.tscapp.ui.model.AvatarParams
import com.itskidan.tscapp.ui.features.call.CallScreenConfig
import com.itskidan.tscapp.ui.features.call.CallScreenConfig.AVATAR_COMPACT_SCALE
import com.itskidan.tscapp.ui.features.call.CallScreenConfig.AVATAR_DEFAULT_SCALE
import com.itskidan.tscapp.ui.features.call.CallScreenConfig.AVATAR_EXPANDED_SCALE
import com.itskidan.tscapp.ui.features.call.CallScreenConfig.AVATAR_MEDIUM_SCALE
import com.itskidan.tscapp.ui.features.call.CallScreenConfig.AVATAR_SCALE_COEFFICIENT

fun getAvatarParams(
    windowSizeClass: WindowWidthSizeClass,
    avatarImage: Any?
): AvatarParams {
    return AvatarParams(
        image = avatarImage,
        scale = getAvatarScale(windowSizeClass),
        minSize = CallScreenConfig.AVATAR_MIN_SIZE,
        maxSize = CallScreenConfig.AVATAR_MAX_SIZE,
        compactBreakpoint = CallScreenConfig.COMPACT_BREAKPOINT,
        description = CallScreenConfig.avatarDescRes.toString(),
        placeholder = CallScreenConfig.avatarPlaceholder
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