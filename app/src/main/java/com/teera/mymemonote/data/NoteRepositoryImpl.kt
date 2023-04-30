package com.teera.mymemonote.data

import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDAO: NoteDAO
) : NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDAO.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDAO.deleteNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDAO.getNote(id)
    }

    override fun getNotes(): Flow<List<Note>> {
        return noteDAO.getAllNote()
    }
}