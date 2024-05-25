package com.example.travelgallery.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelgallery.database.repository.PinRepository
import com.example.travelgallery.ui.uistate.PinDataDetails
import com.example.travelgallery.ui.uistate.PinDataState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PinEditViewModel(
    private val pinRepository: PinRepository,
) : ViewModel() {
    var pinDataState by mutableStateOf(PinDataState())
        private set

    fun updatePin(id: Long) {
        viewModelScope.launch {
            pinDataState =
                pinRepository.getPinStreamById(id)
                    .filterNotNull()
                    .first()
                    .toPinDataState()
        }
    }

    fun updatePinDataState(pinDataDetails: PinDataDetails) {
        pinDataState =
            PinDataState(
                pinDataDetails = pinDataDetails,
            )
    }

    suspend fun updatePinData() {
        pinRepository.updatePinData(pinDataState.pinDataDetails.toPinEntity())
    }
}
