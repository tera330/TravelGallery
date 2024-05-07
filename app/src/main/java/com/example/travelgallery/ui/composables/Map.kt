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
import androidx.compose.ui.tooling.preview.Preview
import com.example.travelgallery.ui.uistate.MapUiState
import com.example.travelgallery.ui.uistate.PinDataState
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
    pinDataState: PinDataState,
    inputTitleStr: (String) -> Unit,
    inputSnippetStr: (String) -> Unit,
    saveLatLng: (Double, Double) -> Unit,
    updateBottomSheetState: (Boolean) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val tokyo = LatLng(35.6894, 139.6917)
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(tokyo, 10f)
        }
    var markers by remember { mutableStateOf(listOf<LatLng>()) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            if (isAddMode) {
                markers = markers + latLng
                saveLatLng(latLng.latitude, latLng.longitude)
                enableAddMarkerMode(false)
                scope.launch(Dispatchers.Main) {
                    delay(500L)
                    updateBottomSheetState(true)
                }
            }
        },
    ) {
        markers.forEach { marker ->
            Marker(
                state = MarkerState(position = marker),
                title = pinDataState.inputTitleStr,
                snippet = pinDataState.inputSnippetStr,
                draggable = false,
                onClick = {
                    false
                },
            )
        }
    }

    if (mapUiState.bottomSheetState) {
        PinSettingBottomSheet(
            isBottomSheetVisible = true,
            pinDataState = pinDataState,
            inputSnippetStr = inputSnippetStr,
            inputTitleStr = inputTitleStr,
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
        pinDataState = PinDataState(),
        inputTitleStr = {},
        inputSnippetStr = {},
        saveLatLng = { x, y -> },
        updateBottomSheetState = {},
    )
}
