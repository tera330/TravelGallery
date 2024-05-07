package com.example.travelgallery.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelgallery.ui.composables.Map
import com.example.travelgallery.ui.uistate.MapUiState
import com.example.travelgallery.ui.uistate.PinDataDetails
import com.example.travelgallery.ui.uistate.PinDataState

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    mapUiState: MapUiState,
    pinDataState: PinDataState,
    onValueChange: (PinDataDetails) -> Unit,
    updateBottomSheetState: (Boolean) -> Unit,
    enableAddMarkerMode: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Map(
            isAddMode = mapUiState.isPinAddMode,
            mapUiState = mapUiState,
            pinDataState = pinDataState,
            enableAddMarkerMode = enableAddMarkerMode,
            onValueChange = onValueChange,
            updateBottomSheetState = updateBottomSheetState,
        )
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
        ) {
            FloatingActionButton(
                modifier = modifier.padding(10.dp),
                shape = CircleShape,
                containerColor = Color.White,
                contentColor = Color.Black,
                onClick = {
                    enableAddMarkerMode(true)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Place",
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        mapUiState = MapUiState(),
        pinDataState = PinDataState(),
        onValueChange = {},
        updateBottomSheetState = {},
        enableAddMarkerMode = {},
    )
}
