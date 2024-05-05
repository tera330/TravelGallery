package com.example.travelgallery.ui.uistate

data class MapUiState(
    val isPinAddMode: Boolean = false,
    val inputTitleStr: String = "",
    val inputSnippetStr: String = "",
    val bottomSheetState: Boolean = false,
)
