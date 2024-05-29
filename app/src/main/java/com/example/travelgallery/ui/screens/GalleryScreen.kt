package com.example.travelgallery.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.travelgallery.ui.composables.AppBar

@ExperimentalMaterial3Api
@Composable
fun GalleryScreen(
    galleryId: Int,
    snippet: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            AppBar(
                canNavigateBack = canNavigateBack,
                navigateUp = { navigateUp() },
                title = snippet,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
        ) {
            LazyVerticalGrid(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                columns = GridCells.Adaptive(minSize = 80.dp),
            ) {
                items(10) {
                    Column {
                        Box(
                            modifier =
                                modifier
                                    .aspectRatio(1f)
                                    .background(Color.LightGray),
                        )
                    }
                }
            }
        }
    }
}
