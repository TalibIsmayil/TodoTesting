package com.scrumlaunch.todotesting.data.repository

import com.scrumlaunch.todotesting.data.datasource.NoteDao
import com.scrumlaunch.todotesting.domain.model.Note
import com.scrumlaunch.todotesting.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(folderId: Int): Flow<List<Note>> = dao.getNotes(folderId)

    override suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id)

    override suspend fun insertNote(note: Note) = dao.insertNote(note)

    override suspend fun deleteNote(note: Note) = dao.deleteNote(note)

    override suspend fun deleteNotesById(folderId: Int) = dao.deleteNotesById(folderId)
}