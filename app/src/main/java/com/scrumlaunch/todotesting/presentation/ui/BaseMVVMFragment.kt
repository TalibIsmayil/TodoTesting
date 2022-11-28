package com.scrumlaunch.todotesting.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseMVVMFragment<VBinding : ViewDataBinding, VM : ViewModel>(
    private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : Fragment() {

    open var useSharedViewModel: Boolean = false

    lateinit var viewModel: VM

    private var _vBinding: VBinding? = null
    val binding: VBinding get() = _vBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vBinding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setUpViews()
        observeData()
    }

    open fun setUpViews() {}

    open fun observeData() {}

    private fun init() {
        viewModel = if (useSharedViewModel) {
            ViewModelProvider(requireActivity())[viewModelClass]
        } else {
            ViewModelProvider(this)[viewModelClass]
        }
    }

    override fun onDestroyView() {
        _vBinding = null
        super.onDestroyView()
    }

}