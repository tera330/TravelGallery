package com.example.travelgallery.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelgallery.ui.composables.BottomNavigationBar
import com.example.travelgallery.ui.data.Screen
import com.example.travelgallery.ui.data.bottomNavItems
import com.example.travelgallery.ui.screens.AllGalleryScreen
import com.example.travelgallery.ui.screens.HomeScreen
import com.example.travelgallery.ui.uistate.MapUiState
import com.example.travelgallery.ui.viewmodel.MapViewModel

@ExperimentalMaterial3Api
@Composable
fun MainHost(
    navController: NavHostController,
    mapUiState: MapUiState,
    enableAddMarkerMode: (Boolean) -> Unit,
    inputTitleStr: (String) -> Unit,
    inputSnippetStr: (String) -> Unit,
    updateBottomSheetState: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                tabItems = bottomNavItems,
            )
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
                    inputTitleStr = inputTitleStr,
                    inputSnippetStr = inputSnippetStr,
                    updateBottomSheetState = updateBottomSheetState,
                )
            }
            composable(route = Screen.AllGallery.name) {
                AllGalleryScreen()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun PreviewMainHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val mapViewModel: MapViewModel = viewModel()
    val mapUiState = mapViewModel.uiState.collectAsState().value

    MainHost(
        navController = navController,
        mapUiState = mapUiState,
        enableAddMarkerMode = {},
        inputTitleStr = {},
        inputSnippetStr = {},
        updateBottomSheetState = {},
        modifier = modifier,
    )
}
