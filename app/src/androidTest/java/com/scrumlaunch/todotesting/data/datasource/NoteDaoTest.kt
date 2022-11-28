package com.scrumlaunch.todotesting.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.scrumlaunch.todotesting.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: NoteDatabase
    private lateinit var dao: NoteDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.noteDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertNoteItem() = runBlocking {
        val note = Note("Title","Content",0,1,1)
        dao.insertNote(note)

        val allNotes = dao.getNotes(1).first()

        assertThat(allNotes).contains(note)
    }

    @Test
    fun getNoteById() = runBlocking {
        val note = Note("Title","Content",0,1,1)
        dao.insertNote(note)
        val insertedNote = dao.getNoteById(1)

        assertThat(note).isEqualTo(insertedNote)
    }

    @Test
    fun deleteNoteItem() = runBlocking {
        val note = Note("Title","Content",0,1,1)
        dao.insertNote(note)
        dao.deleteNote(note)

        val allNotes = dao.getNotes(1).first()

        assertThat(allNotes).doesNotContain(note)
    }
}