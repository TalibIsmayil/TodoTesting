package com.scrumlaunch.todotesting.data.repository

import com.scrumlaunch.todotesting.data.datasource.FolderDao
import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

class FolderRepositoryImpl(
    private val dao: FolderDao
) : FolderRepository {

    override fun getFolders(): Flow<List<Folder>> = dao.getFolders()

    override suspend fun insertFolder(folder: Folder) = dao.insertFolder(folder)

    override suspend fun deleteFolder(folder: Folder) = dao.deleteFolder(folder)
}