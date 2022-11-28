package com.scrumlaunch.todotesting.presentation.folders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scrumlaunch.todotesting.databinding.FoldersListItemBinding
import com.scrumlaunch.todotesting.domain.model.Folder

class FoldersAdapter :
    RecyclerView.Adapter<FoldersAdapter.FoldersRVViewHolder>() {

    private var list: MutableList<Folder> = mutableListOf()

    fun setList(newList: List<Folder>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


    inner class FoldersRVViewHolder(private val binding: FoldersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Folder) {
            binding.folder = model
            binding.root.setOnClickListener { clickListener?.invoke(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoldersRVViewHolder {
        val binding: FoldersListItemBinding =
            FoldersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoldersRVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoldersRVViewHolder, position: Int) {
        list.getOrNull(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = list.size

    private var clickListener: ((Folder) -> Unit)? = null

    fun setOnItemClickListener(listener: (Folder) -> Unit) {
        clickListener = listener
    }
}