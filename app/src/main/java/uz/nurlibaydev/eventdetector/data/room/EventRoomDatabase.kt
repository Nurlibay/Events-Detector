package uz.nurlibaydev.eventdetector.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:04
 */
@Database(
    entities = [EventEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EventRoomDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}