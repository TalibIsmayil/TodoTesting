package com.scrumlaunch.todotesting.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null,
    val folderId: Int
)

class InvalidNoteException(message: String) : Exception(message)