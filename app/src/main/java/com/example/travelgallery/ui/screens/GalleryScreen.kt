package com.example.travelgallery.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GalleryScreen(galleryId: Int) {
    Column {
        Text(text = galleryId.toString())
    }
}
