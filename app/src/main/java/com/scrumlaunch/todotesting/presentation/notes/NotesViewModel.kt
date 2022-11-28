package com.scrumlaunch.todotesting.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.usecase.folders.FolderUseCases
import com.scrumlaunch.todotesting.domain.usecase.notes.NoteUseCases
import com.scrumlaunch.todotesting.domain.util.NoteOrder
import com.scrumlaunch.todotesting.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    private val folderUseCases: FolderUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state: StateFlow<NotesState> = _state.asStateFlow()


    private var folder: Folder? = null
    private var getNotesJob: Job? = null

    var currentContentFilter: OrderType = OrderType.Descending
    var currentTypeFilter: NoteOrder = NoteOrder.Date(currentContentFilter)

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> getNotes(folder!!, event.noteOrder)
            is NotesEvent.AddNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote.invoke(event.note)
                }
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    fun deleteFolder(folder: Folder) = viewModelScope.launch {
        folderUseCases.deleteFolder.invoke(folder)
    }

    fun deleteFolderNotes(folder: Folder) = viewModelScope.launch {
        noteUseCases.deleteNotesById.invoke(folder.id!!)
    }

    fun getNotes(
        folder: Folder,
        noteOrder: NoteOrder = currentTypeFilter
    ) {
        this.folder = folder
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes.invoke(folder.id!!, noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }
}