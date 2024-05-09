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
import com.example.travelgallery.ui.uistate.PinDataDetails
import com.example.travelgallery.ui.uistate.PinDataState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun Map(
    isAddMode: Boolean,
    enableAddMarkerMode: (Boolean) -> Unit,
    mapUiState: MapUiState,
    pinDataState: PinDataState,
    onValueChange: (PinDataDetails) -> Unit,
    updateBottomSheetState: (Boolean) -> Unit,
    insertPinData: () -> Unit
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
                onValueChange(
                    pinDataState.pinDataDetails.copy(
                        latLng = LatLng(latLng.latitude, latLng.longitude),
                    ),
                )
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
                title = pinDataState.pinDataDetails.inputTitleStr,
                snippet = pinDataState.pinDataDetails.inputSnippetStr,
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
            pinDataDetails = pinDataState.pinDataDetails,
            onValueChange = onValueChange,
            insertPinData = insertPinData
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
        onValueChange = {},
        updateBottomSheetState = {},
        insertPinData = {}
    )
}
