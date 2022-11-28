package com.scrumlaunch.todotesting.presentation.notes

import com.scrumlaunch.todotesting.domain.model.Note
import com.scrumlaunch.todotesting.domain.util.NoteOrder


sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class AddNote(val note: Note) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
