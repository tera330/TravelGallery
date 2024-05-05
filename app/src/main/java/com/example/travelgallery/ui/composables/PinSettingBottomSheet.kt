package com.example.travelgallery.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun PinSettingBottomSheet(
    modifier: Modifier = Modifier,
    isBottomSheetVisible: Boolean
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    if (isBottomSheetVisible) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                Column(
                    modifier = modifier
                        .heightIn(min = 200.dp)
                        .fillMaxSize()
                ) {
                    var title by remember { mutableStateOf("") }
                    var snippet by remember { mutableStateOf("") }
                    OutlinedTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("旅行先・地名") }
                    )
                    OutlinedTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        value = snippet,
                        onValueChange = { snippet = it },
                        label = { Text("メモ") }
                    )
                }
            }
        ) {
                LaunchedEffect(Unit) {
                    scaffoldState.bottomSheetState.expand()
                }
        }
    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun PreviewPinSettingBottomSheet() {
    PinSettingBottomSheet(isBottomSheetVisible = true)
}