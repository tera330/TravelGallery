package com.example.travelgallery.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelgallery.ui.composables.Map

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // todo viewmodelで状態として扱う
    var isAddMode by remember { mutableStateOf(true) }
    var isDeleteMode by remember { mutableStateOf(true) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Map(
            isAddMode = isAddMode,
            isDeleteMode = isDeleteMode,
        )
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
        ) {
            FloatingActionButton(
                modifier = modifier.padding(10.dp),
                shape = CircleShape,
                containerColor = Color.White,
                contentColor = Color.Black,
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Place",
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}
