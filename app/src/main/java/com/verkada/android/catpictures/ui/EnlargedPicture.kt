package com.verkada.android.catpictures.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import com.verkada.android.catpictures.R
import com.verkada.android.catpictures.data.Picture

@Composable
fun EnlargedPicture(
    picture: Picture?,
    isFav: Boolean,
    onClickFavIcon: (Picture) -> Unit
) {
    val backgroundColor = remember { mutableStateOf(Color.White) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.333333f)
            .padding(12.dp)
            .background(backgroundColor.value),
        contentAlignment = Alignment.Center
    ) {
        if (picture != null) {
            AsyncImage(
                modifier = Modifier.padding(24.dp),
                model = picture.url,
                contentDescription = null,
                onSuccess = {
                    // Converts the image to a bitmap, then uses Palette to extract a color
                    val bitmap = it.result.drawable.toBitmap()
                    val mutableBitmap = bitmap.copy(Bitmap.Config.RGB_565, true)
                    val palette = Palette.from(mutableBitmap).generate()
                    val color = Color(palette.getLightMutedColor(Color.White.toArgb()))
                    backgroundColor.value = color
                }
            )
            val favIconId = when (isFav) {
                true -> R.drawable.fav_filled
                false -> R.drawable.fav_empty
            }

            Image(
                painter = painterResource(favIconId),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable {
                        onClickFavIcon(picture)
                    }
            )
        }
    }
}
