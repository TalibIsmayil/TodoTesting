package com.scrumlaunch.todotesting.domain.usecase.notes

import com.scrumlaunch.todotesting.domain.model.Note
import com.scrumlaunch.todotesting.domain.repository.NoteRepository


class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? = repository.getNoteById(id)
}