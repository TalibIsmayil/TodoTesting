package com.scrumlaunch.todotesting.domain.usecase.folders

import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.repository.FolderRepository


class AddFolder(
    private val repository: FolderRepository
) {

    suspend operator fun invoke(title: String, password: String? = null) {
        repository.insertFolder(
            Folder(
                title = title,
                timestamp = System.currentTimeMillis(),
                password = if (password?.isEmpty() == true) null else password
            )
        )
    }
}