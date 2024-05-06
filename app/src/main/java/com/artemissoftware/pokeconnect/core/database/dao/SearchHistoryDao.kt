package com.artemissoftware.pokeconnect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artemissoftware.pokeconnect.core.database.entities.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SearchHistoryEntity)

    @Query("SELECT COUNT(*) > 0 FROM searchHistory WHERE description = :description AND note IS NOT NULL")
    suspend fun existWithFullData(description: String): Boolean

    @Query("UPDATE searchHistory SET timestamp = :timeStamp WHERE description = :description")
    suspend fun updateTimeStamp(description: String, timeStamp: Date)

    @Query("SELECT * FROM searchHistory ORDER BY timestamp DESC LIMIT 5")
    fun getAll(): Flow<List<SearchHistoryEntity>>
}