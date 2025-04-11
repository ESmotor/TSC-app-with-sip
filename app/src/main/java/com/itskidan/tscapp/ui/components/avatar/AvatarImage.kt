package com.itskidan.tscapp.ui.components.avatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun AvatarImage(
    image: Any?,
    placeholder: Int,
    size: Dp,
    modifier: Modifier = Modifier,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Crop

) {
    val painter = when (image) {
        is Int -> painterResource(id = image)
        else -> rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            error = painterResource(id = placeholder),
            placeholder = painterResource(id = placeholder)

        )
    }
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        contentScale = contentScale
    )
}