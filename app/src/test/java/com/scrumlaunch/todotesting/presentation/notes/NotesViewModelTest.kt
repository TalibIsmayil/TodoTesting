package com.scrumlaunch.todotesting.presentation.notes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.scrumlaunch.todotesting.MainCoroutineRule
import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.model.Note
import com.scrumlaunch.todotesting.domain.repository.FakeFolderRepository
import com.scrumlaunch.todotesting.domain.repository.FakeNoteRepository
import com.scrumlaunch.todotesting.domain.usecase.folders.AddFolder
import com.scrumlaunch.todotesting.domain.usecase.folders.DeleteFolder
import com.scrumlaunch.todotesting.domain.usecase.folders.FolderUseCases
import com.scrumlaunch.todotesting.domain.usecase.folders.GetFolders
import com.scrumlaunch.todotesting.domain.usecase.notes.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var noteRepository: FakeNoteRepository
    private lateinit var folderRepository: FakeFolderRepository
    private lateinit var viewModel: NotesViewModel
    private lateinit var noteUseCases: NoteUseCases
    private lateinit var folderUseCases: FolderUseCases

    @Before
    fun setup() {
        noteRepository = FakeNoteRepository()
        folderRepository = FakeFolderRepository()
        noteUseCases = NoteUseCases(
            getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            deleteNotesById = DeleteNotesById(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository)
        )
        folderUseCases = FolderUseCases(
            getFolders = GetFolders(folderRepository),
            addFolder = AddFolder(folderRepository),
            deleteFolder = DeleteFolder(folderRepository)
        )
        viewModel = NotesViewModel(noteUseCases, folderUseCases)
    }

    @Test
    fun testToggleOrderSection() = runBlocking {
        val firstOrder = viewModel.state.first().isOrderSectionVisible

        viewModel.onEvent(NotesEvent.ToggleOrderSection)

        val lastOrder = viewModel.state.first().isOrderSectionVisible

        assertThat(firstOrder).isEqualTo(lastOrder.not())
    }

    @Test
    fun testAddNote() = runBlocking {
        val folder = Folder("",0,1)
        val note = Note("Title","Content",0,1,folder.id!!)
        viewModel.onEvent(NotesEvent.AddNote(note))

        viewModel.getNotes(folder)
        val notes = viewModel.state.first().notes

        assertThat(notes).contains(note)
    }

    @Test
    fun testDeleteNote() = runBlocking {
        val folder = Folder("",0,1)
        val note = Note("Title","Content",0,1,folder.id!!)
        viewModel.onEvent(NotesEvent.AddNote(note))
        viewModel.onEvent(NotesEvent.DeleteNote(note))

        viewModel.getNotes(folder)
        val notes = viewModel.state.first().notes

        assertThat(notes).doesNotContain(note)
    }
}