package com.scrumlaunch.todotesting.domain.repository

import com.scrumlaunch.todotesting.domain.model.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {

    fun getFolders(): Flow<List<Folder>>

    suspend fun insertFolder(folder: Folder)

    suspend fun deleteFolder(folder: Folder)

}