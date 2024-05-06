package com.example.travelgallery.ui.uistate


data class PinDataState(
    val latLng: LatLng = LatLng(),
    val inputTitleStr: String = "",
    val inputSnippetStr: String = "",
)

data class LatLng(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
