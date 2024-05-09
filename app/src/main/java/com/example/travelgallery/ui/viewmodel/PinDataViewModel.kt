package com.example.travelgallery.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelgallery.database.entity.PinEntity
import com.example.travelgallery.database.repository.PinRepository
import com.example.travelgallery.ui.uistate.PinDataDetails
import com.example.travelgallery.ui.uistate.PinDataState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class PinDataViewModel(private val pinRepository: PinRepository) : ViewModel() {
    var pinDataState by mutableStateOf(PinDataState())
        private set

    fun updatePinDataState(pinDataDetails: PinDataDetails) {
        pinDataState =
            PinDataState(
                pinDataDetails = pinDataDetails,
                isEntryValid = validateInput(pinDataDetails)
            )
    }

    private fun validateInput(pinDataDetails: PinDataDetails = pinDataState.pinDataDetails): Boolean {
        return with(pinDataDetails) {
            inputTitleStr.isNotBlank() && inputSnippetStr.isNotBlank()
        }
    }

    suspend fun insertPinData() {
        if (validateInput()) {
            viewModelScope.launch {
                pinRepository.insertPinData(pinDataState.pinDataDetails.toPinEntity())
            }
        }
    }

    private fun PinDataDetails.toPinEntity(): PinEntity = PinEntity(
        id = id,
        title = inputTitleStr,
        snippet = inputSnippetStr,
        latitude = latLng.latitude,
        longitude = latLng.longitude
    )
}
