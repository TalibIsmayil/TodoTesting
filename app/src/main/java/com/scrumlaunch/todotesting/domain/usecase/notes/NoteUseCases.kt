package com.scrumlaunch.todotesting.domain.usecase.notes

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val deleteNotesById: DeleteNotesById,
    val addNote: AddNote,
    val getNote: GetNote
)
