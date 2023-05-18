package com.verkada.android.catpictures.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.verkada.android.catpictures.data.Picture

@Composable
internal fun ImageGrid(
    pictures: List<Picture>,
    onPictureClick: (Picture) -> Unit,
    highlighted: String?
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
    ) {
        items(pictures) { picture ->
            SinglePictureInGrid(
                picture = picture,
                onPictureClick = onPictureClick,
                highlighted = picture.id == highlighted,
            )
        }
    }
}
