package com.scrumlaunch.todotesting.presentation.notes

import com.scrumlaunch.todotesting.domain.model.Note
import com.scrumlaunch.todotesting.domain.util.NoteOrder
import com.scrumlaunch.todotesting.domain.util.OrderType


data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
