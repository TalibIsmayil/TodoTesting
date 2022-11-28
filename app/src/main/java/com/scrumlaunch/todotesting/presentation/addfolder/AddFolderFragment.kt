package com.scrumlaunch.todotesting.presentation.addfolder

import androidx.navigation.fragment.findNavController
import com.scrumlaunch.todotesting.R
import com.scrumlaunch.todotesting.databinding.FragmentAddFolderBinding
import com.scrumlaunch.todotesting.presentation.folders.FoldersViewModel
import com.scrumlaunch.todotesting.presentation.ui.BaseMVVMFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFolderFragment : BaseMVVMFragment<FragmentAddFolderBinding, FoldersViewModel>(
    R.layout.fragment_add_folder, FoldersViewModel::class.java
) {

    override var useSharedViewModel: Boolean = true

    override fun setUpViews() {
        binding.detailToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.btnSave.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val password = binding.edtPassword.text.toString()
            viewModel.addNewFolder(title, password)
            findNavController().navigateUp()
        }
    }
}