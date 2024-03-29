package uz.nurlibaydev.eventdetector.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.nurlibaydev.eventdetector.data.room.EventEntity

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:14
 */

interface EventRepository {
    suspend fun insertEventData(eventEntity: EventEntity)
    suspend fun updateEventData(eventEntity: EventEntity)
    fun getAllEvents(): Flow<List<EventEntity>>
    suspend fun enableAllEvents()
    suspend fun disableAllEvents()
}