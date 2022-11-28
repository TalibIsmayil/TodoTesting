package com.scrumlaunch.todotesting.di

import android.app.Application
import androidx.room.Room
import com.scrumlaunch.todotesting.data.datasource.NoteDatabase
import com.scrumlaunch.todotesting.data.repository.FolderRepositoryImpl
import com.scrumlaunch.todotesting.data.repository.NoteRepositoryImpl
import com.scrumlaunch.todotesting.domain.repository.FolderRepository
import com.scrumlaunch.todotesting.domain.repository.NoteRepository
import com.scrumlaunch.todotesting.domain.usecase.folders.AddFolder
import com.scrumlaunch.todotesting.domain.usecase.folders.DeleteFolder
import com.scrumlaunch.todotesting.domain.usecase.folders.FolderUseCases
import com.scrumlaunch.todotesting.domain.usecase.folders.GetFolders
import com.scrumlaunch.todotesting.domain.usecase.notes.*
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = arrayOf(AppModule::class))
object TestAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            NoteDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideFolderRepository(db: NoteDatabase): FolderRepository {
        return FolderRepositoryImpl(db.folderDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            deleteNotesById = DeleteNotesById(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFolderUseCases(repository: FolderRepository): FolderUseCases {
        return FolderUseCases(
            getFolders = GetFolders(repository),
            addFolder = AddFolder(repository),
            deleteFolder = DeleteFolder(repository)
        )
    }
}