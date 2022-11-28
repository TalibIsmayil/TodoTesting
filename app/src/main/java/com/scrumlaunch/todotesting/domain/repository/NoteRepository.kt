package com.scrumlaunch.todotesting.domain.repository

import com.scrumlaunch.todotesting.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(folderId: Int): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun deleteNotesById(folderId: Int)
}