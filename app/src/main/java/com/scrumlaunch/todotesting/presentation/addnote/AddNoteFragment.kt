package com.scrumlaunch.todotesting.presentation.addnote

import androidx.navigation.fragment.findNavController
import com.scrumlaunch.todotesting.R
import com.scrumlaunch.todotesting.databinding.FragmentAddNoteBinding
import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.model.Note
import com.scrumlaunch.todotesting.presentation.folders.FoldersFragment
import com.scrumlaunch.todotesting.presentation.notes.NotesEvent
import com.scrumlaunch.todotesting.presentation.notes.NotesViewModel
import com.scrumlaunch.todotesting.presentation.ui.BaseMVVMFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : BaseMVVMFragment<FragmentAddNoteBinding, NotesViewModel>(
    R.layout.fragment_add_note, NotesViewModel::class.java
) {

    private val folder: Folder by lazy {
        arguments?.getParcelable(FoldersFragment.FOLDER)!!
    }

    override fun setUpViews() {
        binding.detailToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.btnSave.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val content = binding.edtContent.text.toString()
            viewModel.onEvent(
                NotesEvent.AddNote(
                    Note(
                        title,
                        content,
                        System.currentTimeMillis(),
                        folderId = folder.id!!
                    )
                )
            )
            findNavController().popBackStack()
        }
    }
}