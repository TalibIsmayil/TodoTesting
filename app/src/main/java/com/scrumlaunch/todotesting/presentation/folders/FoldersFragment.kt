package com.scrumlaunch.todotesting.presentation.folders

import android.app.AlertDialog
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.scrumlaunch.todotesting.R
import com.scrumlaunch.todotesting.databinding.FragmentFoldersBinding
import com.scrumlaunch.todotesting.domain.model.Folder
import com.scrumlaunch.todotesting.presentation.ui.BaseMVVMFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class FoldersFragment : BaseMVVMFragment<FragmentFoldersBinding, FoldersViewModel>(
    R.layout.fragment_folders, FoldersViewModel::class.java
) {

    companion object {
        const val FOLDER = "folder"
    }

    private val mAdapter: FoldersAdapter by lazy { FoldersAdapter() }

    override fun setUpViews() {
        mAdapter.setOnItemClickListener { folder ->
            if (folder.password == null) {
                navigateToNotes(folder)
            } else {
                showPassLock(folder)
            }
        }
        binding.foldersRV.adapter = mAdapter
        binding.fab.setOnClickListener { findNavController().navigate(R.id.action_to_addFolderFragment) }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.folders.collectLatest { mAdapter.setList(it) }
        }
    }

    private fun navigateToNotes(folder: Folder) {
        findNavController().navigate(
            R.id.action_to_notesFragment, bundleOf(FOLDER to folder)
        )
    }

    private fun showPassLock(folder: Folder) {
        val alert: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val edittext = EditText(requireContext())
        edittext.inputType = InputType.TYPE_CLASS_NUMBER
        alert.setTitle("Enter password")
        alert.setView(edittext)

        alert.setPositiveButton("Unlock") { _, _ ->
            val typedPass = edittext.text.toString().trim()
            if (typedPass == folder.password) {
                navigateToNotes(folder)
            } else {
                Toast.makeText(requireContext(), "Password is incorrect!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        alert.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }

        alert.show()
    }

}