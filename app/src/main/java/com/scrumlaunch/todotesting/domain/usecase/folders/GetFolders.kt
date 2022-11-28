package com.scrumlaunch.todotesting.domain.usecase.folders


import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

class GetFolders(
    private val repository: FolderRepository
) {

    operator fun invoke(): Flow<List<Folder>> = repository.getFolders()
}