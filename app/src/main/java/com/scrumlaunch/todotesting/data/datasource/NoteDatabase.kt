package com.scrumlaunch.todotesting.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.model.Note

@Database(
    entities = [Note::class, Folder::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao
    abstract val folderDao: FolderDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}