package com.scrumlaunch.todotesting.domain.repository

import com.scrumlaunch.todotesting.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepository : NoteRepository {

    private val notes = mutableListOf<Note>()

    override fun getNotes(folderId: Int): Flow<List<Note>> {
        return flow { emit(notes.filter { it.folderId == folderId }) }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id }
    }

    override suspend fun insertNote(note: Note) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }

    override suspend fun deleteNotesById(folderId: Int) {
        val note = notes.find { it.folderId == folderId }
        notes.remove(note)
    }
}