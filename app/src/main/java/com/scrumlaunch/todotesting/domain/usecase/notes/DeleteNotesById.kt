package com.scrumlaunch.todotesting.domain.usecase.notes

import com.scrumlaunch.todotesting.domain.repository.NoteRepository

class DeleteNotesById(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(folderId: Int) = repository.deleteNotesById(folderId)
}