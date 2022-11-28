package com.scrumlaunch.todotesting.presentation.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scrumlaunch.todotesting.databinding.NotesListItemBinding
import com.scrumlaunch.todotesting.domain.model.Note

class NotesAdapter :
    RecyclerView.Adapter<NotesAdapter.NotesRVViewHolder>() {

    private var list: MutableList<Note> = mutableListOf()

    fun setList(newList: List<Note>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


    inner class NotesRVViewHolder(private val binding: NotesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Note) {
            binding.note = model
            binding.root.setOnClickListener { }
            binding.delete.setOnClickListener { clickListener?.invoke(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesRVViewHolder {
        val binding: NotesListItemBinding =
            NotesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesRVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesRVViewHolder, position: Int) {
        list.getOrNull(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = list.size

    private var clickListener: ((Note) -> Unit)? = null

    fun setOnDeleteClickListener(listener: (Note) -> Unit) {
        clickListener = listener
    }
}