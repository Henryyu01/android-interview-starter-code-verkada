package com.verkada.android.catpictures.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.verkada.android.catpictures.data.Picture

@Composable
fun SinglePictureInGrid(
    picture: Picture,
    highlighted: Boolean = false,
    onPictureClick: (Picture) -> Unit,
) {
    val colorFilter =
        if (highlighted)
            ColorFilter.tint(Color.Magenta, blendMode = BlendMode.Overlay)
        else
            null
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable { onPictureClick(picture) }
    ) {
        AsyncImage(
            model = picture.url,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            colorFilter = colorFilter,
            modifier = Modifier.padding(1.dp)
        )
    }
}
