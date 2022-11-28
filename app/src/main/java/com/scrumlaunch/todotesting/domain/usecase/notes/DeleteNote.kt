package com.scrumlaunch.todotesting.domain.usecase.notes

import com.scrumlaunch.todotesting.domain.model.Note
import com.scrumlaunch.todotesting.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) = repository.deleteNote(note)
}