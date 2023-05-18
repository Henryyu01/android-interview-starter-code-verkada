package com.verkada.android.catpictures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.verkada.android.catpictures.theme.CatPicturesTheme
import com.verkada.android.catpictures.viewmodel.PictureViewModel

class MainComposeActivity : ComponentActivity() {

    private val pictureViewModel: PictureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CatPicturesTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            Screens.values().forEach { screen ->
                                BottomNavigationItem(
                                    selected = currentDestination?.hierarchy?.any {
                                        it.route == screen.name
                                    }.isTrue(),
                                    label = { Text(text = screen.name) },
                                    onClick = {
                                        pictureViewModel.deselectPicture()
                                        navController.navigate(screen.name) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        val id = when (screen) {
                                            Screens.Home -> R.drawable.home
                                            Screens.Favorites -> R.drawable.fav_filled
                                        }
                                        Icon(
                                            painter = painterResource(id = id),
                                            modifier = Modifier.size(24.dp),
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colors.background
                    ) {
                        CatPicturesNavHost(pictureViewModel = pictureViewModel, navController = navController)
                    }
                }
            }
        }
    }
}

fun Boolean?.isTrue() = this == true
