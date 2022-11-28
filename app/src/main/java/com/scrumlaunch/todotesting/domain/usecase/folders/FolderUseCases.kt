package com.scrumlaunch.todotesting.domain.usecase.folders

data class FolderUseCases(
    val getFolders: GetFolders,
    val addFolder: AddFolder,
    val deleteFolder: DeleteFolder
)
