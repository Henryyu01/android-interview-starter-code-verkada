package com.verkada.android.catpictures.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verkada.android.catpictures.data.Picture
import com.verkada.android.catpictures.data.PictureRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PictureViewModel : ViewModel() {

    private val pictureRepository: PictureRepository = PictureRepository()

    // State information
    private val mutablePictures = pictureRepository.pictures
    val pictures: StateFlow<List<Picture>> = mutablePictures

    private val mutableFavoritePictures = pictureRepository.favoritePictures
    val favoritePictures: StateFlow<List<Picture>> = mutableFavoritePictures

    private val mutableSelectedPicture: MutableStateFlow<Picture?> = MutableStateFlow(null)
    val selectedPicture: StateFlow<Picture?> = mutableSelectedPicture

    init {
        // Refresh images on app start
        viewModelScope.launch {
            pictureRepository.refreshPictures()
        }
    }

    /**
     * Select/Deselects the given [picture]
     */
    fun onPictureClick(picture: Picture) {
        mutableSelectedPicture.value = if (picture == mutableSelectedPicture.value) {
            null
        } else {
            picture
        }
    }

    /**
     * Favorites/unfavorites the given [picture]
     */
    fun onPictureFavorite(picture: Picture) {
        val isFavorite = picture in favoritePictures.value
        pictureRepository.onPictureFavorite(picture, isFavorite)
    }

    /**
     * Favorites/unfavorites and deselects the given [picture]
     */
    fun onPictureFavoriteDeselect(picture: Picture) {
        onPictureFavorite(picture)
        mutableSelectedPicture.value = null
    }

    /**
     * Deselect any currently selected picture
     */
    fun deselectPicture() {
        mutableSelectedPicture.value = null
    }
}
