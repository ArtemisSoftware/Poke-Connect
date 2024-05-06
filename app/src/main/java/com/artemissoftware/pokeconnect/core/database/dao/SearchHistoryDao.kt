package com.artemissoftware.pokeconnect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.artemissoftware.pokeconnect.core.database.entities.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SearchHistoryEntity)

    @Update
    suspend fun update(entity: SearchHistoryEntity)

    @Query("SELECT * FROM searchHistory ORDER BY timestamp DESC LIMIT 5")
    fun getAll(): Flow<List<SearchHistoryEntity>>
}