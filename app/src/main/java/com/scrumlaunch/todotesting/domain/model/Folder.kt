package com.scrumlaunch.todotesting.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Folder(
    val title: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null,
    val password: String? = null
) : Parcelable

