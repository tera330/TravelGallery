package com.example.travelgallery.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.travelgallery.ui.composables.BottomNavigationBar
import com.example.travelgallery.ui.data.Screen
import com.example.travelgallery.ui.data.bottomNavItems
import com.example.travelgallery.ui.screens.AllGalleryScreen
import com.example.travelgallery.ui.screens.GalleryScreen
import com.example.travelgallery.ui.screens.HomeScreen
import com.example.travelgallery.ui.uistate.HomeUiState
import com.example.travelgallery.ui.uistate.MapUiState

@ExperimentalMaterial3Api
@Composable
fun MainHost(
    navController: NavHostController,
    mapUiState: MapUiState,
    enableAddMarkerMode: (Boolean) -> Unit,
    updateBottomSheetState: (Boolean) -> Unit,
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (backStackEntry?.destination?.route.toString() != "GalleryScreen/{galleryId}/{snippet}") {
                BottomNavigationBar(
                    navController = navController,
                    tabItems = bottomNavItems,
                )
            }
        },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            composable(route = Screen.Home.name) {
                HomeScreen(
                    mapUiState = mapUiState,
                    enableAddMarkerMode = { boolean -> enableAddMarkerMode(boolean) },
                    updateBottomSheetState = updateBottomSheetState,
                    homeUiState = homeUiState,
                    navigateGalleryScreen = { galleryId, snippet ->
                        navController.navigate("${Screen.GalleryScreen.name}/$galleryId/$snippet")
                    },
                )
            }
            composable(route = Screen.AllGallery.name) {
                AllGalleryScreen()
            }
            composable(
                route = "${Screen.GalleryScreen.name}/{galleryId}/{snippet}",
                arguments =
                    listOf(
                        navArgument("galleryId") { type = NavType.IntType },
                        navArgument("snippet") { type = NavType.StringType },
                    ),
            ) { backStackEntry ->
                val galleryId = backStackEntry.arguments?.getInt("galleryId") ?: 0
                val snippet = backStackEntry.arguments?.getString("snippet") ?: ""
                GalleryScreen(
                    galleryId = galleryId,
                    snippet = snippet,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun PreviewMainHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    MainHost(
        navController = navController,
        mapUiState = MapUiState(),
        enableAddMarkerMode = {},
        updateBottomSheetState = {},
        homeUiState = HomeUiState(),
        modifier = modifier,
    )
}
