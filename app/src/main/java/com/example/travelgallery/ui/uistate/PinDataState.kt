package com.example.travelgallery.ui.uistate

import com.google.android.gms.maps.model.LatLng

data class PinDataState(
    var pinDataDetails: PinDataDetails = PinDataDetails(),
    var currentPinId: Long = 1,
)

data class PinDataDetails(
    val id: Int = 0,
    var latLng: LatLng = LatLng(0.0, 0.0),
    val inputTitleStr: String = "",
    val inputSnippetStr: String = "",
)
