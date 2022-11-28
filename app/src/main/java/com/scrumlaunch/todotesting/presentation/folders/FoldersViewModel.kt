package com.scrumlaunch.todotesting.presentation.folders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.usecase.folders.FolderUseCases
import com.scrumlaunch.todotesting.domain.usecase.folders.GetFolders
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoldersViewModel @Inject constructor(
    private val folderUseCases: FolderUseCases
) : ViewModel() {

    val folders: StateFlow<List<Folder>> = folderUseCases.getFolders.invoke().stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )

    fun addNewFolder(title: String, password: String) {
        viewModelScope.launch {
            folderUseCases.addFolder.invoke(title, password)
        }
    }

}