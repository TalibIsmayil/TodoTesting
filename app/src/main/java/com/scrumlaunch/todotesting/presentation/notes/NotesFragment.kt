package com.scrumlaunch.todotesting.presentation.notes

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.scrumlaunch.todotesting.R
import com.scrumlaunch.todotesting.databinding.FragmentNotesBinding
import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.domain.util.NoteOrder
import com.scrumlaunch.todotesting.domain.util.OrderType
import com.scrumlaunch.todotesting.presentation.folders.FoldersFragment
import com.scrumlaunch.todotesting.presentation.ui.BaseMVVMFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NotesFragment : BaseMVVMFragment<FragmentNotesBinding, NotesViewModel>(
    R.layout.fragment_notes, NotesViewModel::class.java
) {

    private val folder: Folder by lazy {
        arguments?.getParcelable(FoldersFragment.FOLDER)!!
    }

    private val mAdapter: NotesAdapter by lazy { NotesAdapter() }

    override fun setUpViews() {
        binding.folder = folder
        binding.viewmodel = viewModel
        binding.detailToolbar.apply {
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.delete -> {
                        viewModel.deleteFolder(folder)
                        viewModel.deleteFolderNotes(folder)
                        findNavController().popBackStack()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.filter -> {
                        viewModel.onEvent(NotesEvent.ToggleOrderSection)
                        return@setOnMenuItemClickListener true
                    }
                }
                return@setOnMenuItemClickListener false
            }
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
        binding.contentFilterContainer.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.titleRB -> viewModel.currentTypeFilter =
                    NoteOrder.Title(viewModel.currentContentFilter)
                R.id.dateRB -> viewModel.currentTypeFilter =
                    NoteOrder.Date(viewModel.currentContentFilter)
            }
            viewModel.onEvent(NotesEvent.Order(viewModel.currentTypeFilter))
        }
        binding.typeFilterContainer.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.ascRB -> viewModel.currentContentFilter = OrderType.Ascending
                R.id.dscRB -> viewModel.currentContentFilter = OrderType.Descending
            }
            viewModel.currentTypeFilter =
                viewModel.currentTypeFilter.copy(viewModel.currentContentFilter)
            viewModel.onEvent(NotesEvent.Order(viewModel.currentTypeFilter))
        }
        mAdapter.setOnDeleteClickListener { note -> viewModel.onEvent(NotesEvent.DeleteNote(note)) }
        binding.notesRV.adapter = mAdapter
        binding.fab.setOnClickListener {
            findNavController().navigate(
                R.id.action_to_addNoteFragment,
                arguments
            )
        }
        viewModel.getNotes(folder)
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest {
                mAdapter.setList(it.notes)
            }
        }
    }
}