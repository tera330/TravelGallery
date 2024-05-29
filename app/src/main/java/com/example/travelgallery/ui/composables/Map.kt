package com.example.travelgallery.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelgallery.database.PinDatabase
import com.example.travelgallery.database.repository.PinRepository
import com.example.travelgallery.ui.uistate.HomeUiState
import com.example.travelgallery.ui.uistate.MapUiState
import com.example.travelgallery.ui.viewmodel.PinDataViewModel
import com.example.travelgallery.ui.viewmodel.PinEditViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun Map(
    isAddMode: Boolean,
    enableAddMarkerMode: (Boolean) -> Unit,
    mapUiState: MapUiState,
    updateBottomSheetState: (Boolean) -> Unit,
    homeUiState: HomeUiState,
    navigateGalleryScreen: (Int, String) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val tokyo = LatLng(35.6894, 139.6917)
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(tokyo, 10f)
        }
    var markers by remember { mutableStateOf(listOf<LatLng>()) }

    val pinRepository = PinRepository(PinDatabase.getDatabase(LocalContext.current).pinDao())
    val pinDataViewModel: PinDataViewModel =
        viewModel {
            PinDataViewModel(pinRepository)
        }
    val insertPinDataState = pinDataViewModel.pinDataState

    val pinEditViewModel: PinEditViewModel =
        viewModel {
            PinEditViewModel(pinRepository)
        }
    val getPinDataState = pinEditViewModel.pinDataState

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            scope.launch {
                if (isAddMode) {
                    markers = markers + latLng
                    pinDataViewModel.updatePinDataState(
                        insertPinDataState.pinDataDetails.copy(
                            latLng = LatLng(latLng.latitude, latLng.longitude),
                        ),
                    )
                    pinEditViewModel.updatePin(pinDataViewModel.insertPinData())
                    enableAddMarkerMode(false)
                }
            }
            scope.launch(Dispatchers.Main) {
                delay(500L)
                updateBottomSheetState(true)
            }
        },
    ) {
        homeUiState.taskList.forEach { marker ->
            val markerState = MarkerState(position = LatLng(marker.latitude, marker.longitude))

            // todo 情報ウィンドウの表示
            Marker(
                state = markerState,
                title = marker.title,
                snippet = marker.snippet,
                draggable = false,
                onClick = {
                    navigateGalleryScreen(
                        marker.id,
                        marker.snippet,
                    )
                    false
                },
            )
        }
    }

    if (mapUiState.bottomSheetState) {
        PinSettingBottomSheet(
            isBottomSheetVisible = true,
            pinDataDetails = getPinDataState.pinDataDetails,
            updatePinData = {
                scope.launch {
                    pinEditViewModel.updatePinData()
                }
            },
            onValueChange = { pinDataDetails -> pinEditViewModel.updatePinDataState(pinDataDetails) },
            updateBottomSheetState = updateBottomSheetState,
        )
    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun PreviewMap() {
    Map(
        isAddMode = true,
        enableAddMarkerMode = {},
        mapUiState = MapUiState(),
        updateBottomSheetState = {},
        homeUiState = HomeUiState(),
        navigateGalleryScreen = { a, b -> },
    )
}
