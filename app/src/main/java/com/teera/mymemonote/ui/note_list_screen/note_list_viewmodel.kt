package com.teera.mymemonote.ui.note_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teera.mymemonote.data.Note
import com.teera.mymemonote.data.NoteRepository
import com.teera.mymemonote.utill.Routes
import com.teera.mymemonote.utill.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    val notes = repository.getNotes()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deleteNote: Note? = null

    fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.onAddNoteClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.open_note))
            }

            is NoteListEvent.onNoteClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.open_note + "?noteId=${event.note.id}"))
            }

            is NoteListEvent.onDeleteClick -> {
                viewModelScope.launch {
                    deleteNote = event.note
                    repository.deleteNote(event.note)
                }
            }
        }
    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}