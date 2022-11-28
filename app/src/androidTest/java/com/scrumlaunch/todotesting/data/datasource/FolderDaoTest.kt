package com.scrumlaunch.todotesting.data.datasource

import com.google.common.truth.Truth
import com.scrumlaunch.todotesting.domain.model.Folder
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class FolderDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: NoteDatabase
    private lateinit var dao: FolderDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.folderDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertFolderItem() = runBlocking {
        val folder = Folder("Title",0,1)
        dao.insertFolder(folder)

        val allFolders = dao.getFolders().first()

        Truth.assertThat(allFolders).contains(folder)
    }

    @Test
    fun deleteFolderItem() = runBlocking {
        val folder = Folder("Title",0,1)
        dao.insertFolder(folder)
        dao.deleteFolder(folder)

        val allFolders = dao.getFolders().first()

        Truth.assertThat(allFolders).doesNotContain(folder)
    }
}