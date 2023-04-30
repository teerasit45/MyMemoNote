package com.teera.mymemonote.ui.open_note_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teera.mymemonote.utill.UiEvent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun open_note_screen(
    onPopBackStack:() -> Unit,
    viewModel: OpenNoteViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }
    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.White,
                onClick = {
                    viewModel.onEvent(OpenNoteEvent.onSaveNoteClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save Note",
                    tint = Color(0xFFF38320)
                )
            }
        }
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF38320))
                    .padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    TextField(
                        value = viewModel.title,
                        onValueChange = {
                            viewModel.onEvent(OpenNoteEvent.onTitleChange(it))
                        },

                        placeholder = { Text(text = "Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Box(
                    modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    TextField(
                        value = viewModel.details,
                        onValueChange = {
                            viewModel.onEvent(OpenNoteEvent.onDetailsChange(it))
                        },

                        placeholder = { Text(text = "Details") },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

}