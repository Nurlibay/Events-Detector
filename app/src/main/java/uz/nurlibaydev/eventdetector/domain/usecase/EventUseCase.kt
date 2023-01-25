package uz.nurlibaydev.eventdetector.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.nurlibaydev.eventdetector.data.room.EventEntity

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:12
 */

interface EventUseCase {
    suspend fun insertEventData(eventEntity: EventEntity)
    suspend fun updateEventData(eventEntity: EventEntity)
    fun getAllEvents(): Flow<List<EventEntity>>
    suspend fun enableAllEvents()
    suspend fun disableAllEvents()
}