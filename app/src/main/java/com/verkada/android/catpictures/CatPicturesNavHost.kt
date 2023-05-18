package com.verkada.android.catpictures

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.verkada.android.catpictures.ui.PictureScreen
import com.verkada.android.catpictures.viewmodel.PictureViewModel

@Composable
fun CatPicturesNavHost(
    pictureViewModel: PictureViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val pictures by pictureViewModel.pictures.collectAsState()
    val favoritePictures by pictureViewModel.favoritePictures.collectAsState()
    val selectedPicture by pictureViewModel.selectedPicture.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screens.Home.name,
        modifier = modifier
    ) {
        composable(route = Screens.Home.name) {
            val isFavorite = selectedPicture in favoritePictures

            PictureScreen(
                pictures = pictures,
                selectedPicture = selectedPicture,
                isFavorite = isFavorite,
                onPictureClick = pictureViewModel::onPictureClick,
                onFavoriteClick = pictureViewModel::onPictureFavorite
            )
        }

        composable(route = Screens.Favorites.name) {
            PictureScreen(
                pictures = favoritePictures,
                selectedPicture = selectedPicture,
                isFavorite = true,
                onPictureClick = pictureViewModel::onPictureClick,
                onFavoriteClick = pictureViewModel::onPictureFavoriteDeselect
            )
        }
    }
}

enum class Screens {
    Home,
    Favorites
}
