package com.teera.mymemonote.ui.open_note_screen

sealed class OpenNoteEvent {
    data class onTitleChange(val title: String) : OpenNoteEvent()
    data class onDetailsChange(val details: String) : OpenNoteEvent()
    object onSaveNoteClick : OpenNoteEvent()
}