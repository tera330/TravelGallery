package com.example.travelgallery.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.travelgallery.ui.uistate.PinDataDetails
import com.example.travelgallery.ui.uistate.PinDataState

class PinDataViewModel : ViewModel() {
    var pinDataState by mutableStateOf(PinDataState())
        private set

    fun updatePinDataState(pinDataDetails: PinDataDetails) {
        pinDataState =
            PinDataState(
                pinDataDetails = pinDataDetails, isEntryValid = true, // todo isEntryValidを判定する関数を作成
            )
    }
}
