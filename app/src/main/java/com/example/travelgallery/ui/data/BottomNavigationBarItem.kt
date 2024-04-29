package com.example.travelgallery.ui.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.travelgallery.R

sealed class BottomNavigationBarItem(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
) {
    object Home : BottomNavigationBarItem(
        route = "Home",
        resourceId = R.string.home,
        icon = Icons.Default.Map,
    )

    object AllGallery : BottomNavigationBarItem(
        route = "AllGallery",
        resourceId = R.string.all_gallery,
        icon = Icons.Default.PhotoLibrary,
    )
}
