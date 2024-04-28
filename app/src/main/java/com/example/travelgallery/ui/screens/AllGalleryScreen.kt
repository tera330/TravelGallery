package com.example.travelgallery.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AllGalleryScreen(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        columns = GridCells.Adaptive(minSize = 150.dp),
    ) {
        items(10) {
            Column {
                Box(
                    modifier =
                        modifier
                            .aspectRatio(1f)
                            .background(Color.LightGray, RoundedCornerShape(15.dp)),
                ) {
                }
                Text(
                    text = "place",
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAllGalleryScreen() {
    AllGalleryScreen()
}
