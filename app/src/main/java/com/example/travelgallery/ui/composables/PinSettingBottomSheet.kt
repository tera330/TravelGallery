package com.example.travelgallery.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelgallery.ui.uistate.PinDataState

@ExperimentalMaterial3Api
@Composable
fun PinSettingBottomSheet(
    modifier: Modifier = Modifier,
    isBottomSheetVisible: Boolean,
    pinDataState: PinDataState,
    inputTitleStr: (String) -> Unit,
    inputSnippetStr: (String) -> Unit,
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    if (isBottomSheetVisible) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                Column(
                    modifier =
                        modifier
                            .heightIn(min = 200.dp)
                            .fillMaxSize(),
                ) {
                    OutlinedTextField(
                        modifier =
                            modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                        value = pinDataState.inputTitleStr,
                        onValueChange = { inputTitleStr(it) },
                        label = { Text("旅行先・地名") },
                    )
                    OutlinedTextField(
                        modifier =
                            modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                        value = pinDataState.inputSnippetStr,
                        onValueChange = { inputSnippetStr(it) },
                        label = { Text("メモ") },
                    )
                    Button(
                        onClick = {
                            Log.d("result", pinDataState.latLng.toString())
                        },
                        modifier =
                            Modifier
                                .padding(10.dp)
                                .align(Alignment.End),
                    ) {
                        Text(text = "保存")
                    }
                }
            },
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
    PinSettingBottomSheet(
        isBottomSheetVisible = true,
        pinDataState = PinDataState(),
        inputTitleStr = {},
        inputSnippetStr = {},
    )
}
