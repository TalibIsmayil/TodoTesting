package com.scrumlaunch.todotesting.data.datasource

import androidx.room.*
import com.scrumlaunch.todotesting.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE folderId = :folderId")
    fun getNotes(folderId: Int): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note WHERE folderId = :folderId")
    suspend fun deleteNotesById(folderId: Int)
}