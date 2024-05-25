package com.example.travelgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.travelgallery.database.PinDatabase
import com.example.travelgallery.database.repository.PinRepository
import com.example.travelgallery.ui.MainHost
import com.example.travelgallery.ui.theme.TravelGalleryTheme
import com.example.travelgallery.ui.viewmodel.HomeViewModel
import com.example.travelgallery.ui.viewmodel.MapViewModel

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val mapViewModel: MapViewModel by viewModels()
            val pinRepository = PinRepository(PinDatabase.getDatabase(this).pinDao())

            val homeViewModel: HomeViewModel =
                viewModel {
                    HomeViewModel(pinRepository)
                }
            val homeUiState = homeViewModel.homeUiState.collectAsState().value
            val mapUiState = mapViewModel.uiState.collectAsState().value

            TravelGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainHost(
                        navController = navController,
                        mapUiState = mapUiState,
                        enableAddMarkerMode = { boolean -> mapViewModel.enableAddMarkerMode(boolean) },
                        updateBottomSheetState = { boolean ->
                            mapViewModel.updateBottomSheetState(
                                boolean,
                            )
                        },
                        homeUiState = homeUiState,
                    )
                }
            }
        }
    }
}
