package com.example.travelgallery.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.travelgallery.ui.uistate.MapUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MapViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()

    fun enableAddMarkerMode(flag: Boolean) {
        _uiState.update {
            it.copy(
                isPinAddMode = flag,
            )
        }
    }

    fun updateBottomSheetState(flag: Boolean) {
        _uiState.update {
            it.copy(
                bottomSheetState = flag,
            )
        }
    }
}
