package uz.nurlibaydev.eventdetector.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:05
 */

@Dao
interface EventDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertEvent(eventEntity: EventEntity)

    @Update
    suspend fun updateEvent(eventEntity: EventEntity)

    @Query("SELECT * FROM event_table")
    fun getAllEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM event_table WHERE id=:id")
    suspend fun getEventById(id: Int): EventEntity

    @Query("UPDATE event_table SET status = 1 WHERE status = 0")
    suspend fun enableAllEvents()

    @Query("UPDATE event_table SET status = 0 WHERE status = 1")
    suspend fun disableAllEvents()
}