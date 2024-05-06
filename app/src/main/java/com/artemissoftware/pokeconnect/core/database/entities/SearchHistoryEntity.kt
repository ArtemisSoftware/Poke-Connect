package com.artemissoftware.pokeconnect.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "searchHistory")
data class SearchHistoryEntity(
    @PrimaryKey
    val description: String,
    val note: String? = null,
    val timestamp: Date = Date()
)
