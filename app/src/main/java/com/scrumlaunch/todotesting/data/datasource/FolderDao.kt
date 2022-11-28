package com.scrumlaunch.todotesting.data.datasource

import androidx.room.*
import com.scrumlaunch.todotesting.domain.model.Folder
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {

    @Query("SELECT * FROM folder")
    fun getFolders(): Flow<List<Folder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: Folder)

    @Delete
    suspend fun deleteFolder(folder: Folder)

}