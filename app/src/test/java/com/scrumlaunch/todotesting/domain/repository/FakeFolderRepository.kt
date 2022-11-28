package com.scrumlaunch.todotesting.domain.repository

import com.scrumlaunch.todotesting.domain.model.Folder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFolderRepository : FolderRepository {

    private val folders = mutableListOf<Folder>()

    override fun getFolders(): Flow<List<Folder>> {
        return flow { emit(folders) }
    }

    override suspend fun insertFolder(folder: Folder) {
        folders.add(folder)
    }

    override suspend fun deleteFolder(folder: Folder) {
        folders.remove(folder)
    }


}