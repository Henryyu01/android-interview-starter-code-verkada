package com.verkada.android.catpictures.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.verkada.android.catpictures.data.Picture

@Composable
internal fun PictureScreen(
    pictures: List<Picture>,
    selectedPicture: Picture?,
    isFavorite: Boolean,
    onPictureClick: (Picture) -> Unit,
    onFavoriteClick: (Picture) -> Unit,
) {
    Column() {
        AnimatedVisibility(
            visible = selectedPicture != null,
            enter = slideInVertically(initialOffsetY = { fullHeight -> -fullHeight/2})
        ) {
            EnlargedPicture(
                picture = selectedPicture,
                isFav = isFavorite,
                onClickFavIcon = onFavoriteClick
            )
        }
        ImageGrid(pictures = pictures, onPictureClick = onPictureClick, highlighted = selectedPicture?.id)
    }
}
