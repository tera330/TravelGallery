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

    private fun updateCurrentPinData(currentId: Long) {
        pinDataState =
            pinDataState.copy(
                currentPinId = currentId,
            )
    }

    fun updatePinDataState(pinDataDetails: PinDataDetails) {
        pinDataState =
            PinDataState(
                pinDataDetails = pinDataDetails,
            )
    }

    suspend fun insertPinData(): Long {
        var id: Long = 0
        val job =
            viewModelScope.launch {
                id = pinRepository.insertPinData(pinDataState.pinDataDetails.toPinEntity())
            }
        job.join()
        updateCurrentPinData(id)
        return id
    }
}

fun PinDataDetails.toPinEntity(): PinEntity =
    PinEntity(
        id = id,
        title = inputTitleStr,
        snippet = inputSnippetStr,
        latitude = latLng.latitude,
        longitude = latLng.longitude,
    )

fun PinEntity.toPinDataState(): PinDataState =
    PinDataState(
        pinDataDetails =
            PinDataDetails(
                id = id,
                inputTitleStr = title,
                inputSnippetStr = snippet,
                latLng = LatLng(latitude, longitude),
            ),
    )
