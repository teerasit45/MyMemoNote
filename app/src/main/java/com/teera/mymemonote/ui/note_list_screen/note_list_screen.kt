package com.teera.mymemonote.ui.note_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teera.mymemonote.utill.UiEvent

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: NoteListViewModel = hiltViewModel()
) {

    val notes = viewModel.notes.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.White,
                onClick = { viewModel.onEvent(NoteListEvent.onAddNoteClick) }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Add Note",
                    tint = Color(0xFFF38320)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .background(color = Color(0xFFF38320))
                .fillMaxSize()
        ) {
            items(notes.value) { note ->
                NoteCard(
                    note = note,
                    modifier = Modifier.combinedClickable(
                        onClick = {
                            viewModel.onEvent(NoteListEvent.onNoteClick(note))
                        },
                        onLongClick = {
                            viewModel.onEvent(NoteListEvent.onDeleteClick(note))
                        },

                        ).padding(10.dp)
                        .clip(RoundedCornerShape(20.dp)),
                )
            }
        }

    }
}