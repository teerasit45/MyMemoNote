package com.teera.mymemonote.ui.open_note_screen

import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teera.mymemonote.data.Note
import com.teera.mymemonote.data.NoteRepository
import com.teera.mymemonote.utill.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class OpenNoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var note by mutableStateOf<Note?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var details by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val noteId = savedStateHandle.get<Int>("noteId")!!
        if (noteId != -1) {
            viewModelScope.launch {
                repository.getNoteById(noteId)?.let {note ->
                    title = note.title
                    details = note.details
                    this@OpenNoteViewModel.note = note
                }
            }
        }
    }

    fun onEvent(event: OpenNoteEvent) {
        when(event) {
            is OpenNoteEvent.onTitleChange -> {
                title = event.title
            }

            is OpenNoteEvent.onDetailsChange -> {
                details = event.details
            }

            is OpenNoteEvent.onSaveNoteClick -> {
                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
                val current = formatter.format(Date())
                viewModelScope.launch(Dispatchers.IO) {
                    repository.insertNote(
                        Note(
                            title = title,
                            details = details,
                            timestamp = current,
                            wordCount = wordCount(title,details),
                            id = note?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }

            }
        }
    }

    private fun wordCount(title: String,details: String): Int {
        val trimmedStr = title.trim()
        val trimmedStr2 = details.trim()
        val strlenght: Int
        val strLenght2: Int
        return if (trimmedStr.isEmpty() && trimmedStr2.isEmpty()) {
            0
        } else {
            strlenght = trimmedStr.split("\\s+".toRegex()).size
            strLenght2 = trimmedStr2.split("\\s+".toRegex()).size
            return strlenght + strLenght2
        }
    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}