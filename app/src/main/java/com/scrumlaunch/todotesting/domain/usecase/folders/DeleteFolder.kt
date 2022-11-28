package com.scrumlaunch.todotesting.domain.usecase.folders

import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.repository.FolderRepository


class DeleteFolder(
    private val repository: FolderRepository
) {

    suspend operator fun invoke(folder: Folder) = repository.deleteFolder(folder)
}