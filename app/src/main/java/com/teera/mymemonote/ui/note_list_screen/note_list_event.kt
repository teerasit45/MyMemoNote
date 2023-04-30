package com.teera.mymemonote.ui.note_list_screen

import com.teera.mymemonote.data.Note

sealed class NoteListEvent {
    object onAddNoteClick: NoteListEvent()
    data class onNoteClick(val note: Note): NoteListEvent()
    data class onDeleteClick(val note: Note): NoteListEvent()
}