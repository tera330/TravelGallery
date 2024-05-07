package com.example.travelgallery.ui.uistate

import com.google.android.gms.maps.model.LatLng

data class PinDataState(
    val latLng: LatLng = LatLng(0.0, 0.0),
    val inputTitleStr: String = "",
    val inputSnippetStr: String = "",
)
