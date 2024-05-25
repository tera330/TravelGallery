package com.example.travelgallery.ui.uistate

import com.example.travelgallery.database.entity.PinEntity

data class HomeUiState(
    val taskList: List<PinEntity> = listOf(),
)
