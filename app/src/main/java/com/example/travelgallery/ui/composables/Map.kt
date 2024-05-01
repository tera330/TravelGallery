package com.example.travelgallery.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun Map(
    isAddMode: Boolean,
    enableAddMarkerMode: (Boolean) -> Unit,
) {
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
                enableAddMarkerMode(false)
            } else {
            }
        },
    ) {
        markers.forEach { marker ->
            Marker(
                state = MarkerState(position = marker),
                title = "aa",
                snippet = "aa",
                draggable = true,
                onClick = {
                    false
                },
            )
        }
    }
}

@Preview
@Composable
fun PreviewMap() {
    Map(
        isAddMode = true,
        enableAddMarkerMode = { },
    )
}
