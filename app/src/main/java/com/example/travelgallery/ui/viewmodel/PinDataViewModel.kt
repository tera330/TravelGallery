package com.example.travelgallery.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.travelgallery.ui.uistate.PinDataState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PinDataViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PinDataState())
    val uiState: StateFlow<PinDataState> = _uiState.asStateFlow()

    fun updateInputTitleStr(title: String) {
        _uiState.update {
            it.copy(
                inputTitleStr = title,
            )
        }
    }

    fun updateInputSnippetStr(snippet: String) {
        _uiState.update {
            it.copy(
                inputSnippetStr = snippet,
            )
        }
    }

    fun saveLatLng(
        latitude: Double,
        longitude: Double,
    ) {
        _uiState.update {
            it.copy(
                latLng = LatLng(latitude, longitude),
            )
        }
    }
}
