package com.verkada.android.catpictures.data

import com.verkada.android.catpictures.network.Network
import kotlinx.coroutines.flow.MutableStateFlow

class PictureRepository {

    val pictures: MutableStateFlow<List<Picture>> = MutableStateFlow(emptyList())

    val favoritePictures: MutableStateFlow<List<Picture>> = MutableStateFlow(emptyList())

    suspend fun refreshPictures() {
        pictures.value = Network.service.pictures()
        favoritePictures.value = emptyList()
    }

    /**
     * Favorites/unfavorites the given [picture] based on [isFavorite]]
     */
    fun onPictureFavorite(picture: Picture, isFavorite: Boolean) {
        if (isFavorite) {
            favoritePictures.value = favoritePictures.value - picture
        } else {
            favoritePictures.value = favoritePictures.value + picture
        }
    }
}
