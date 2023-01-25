package uz.nurlibaydev.eventdetector.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.nurlibaydev.eventdetector.data.room.EventEntity
import uz.nurlibaydev.eventdetector.domain.repository.EventRepository
import javax.inject.Inject

class EventUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository
) : EventUseCase {

    override suspend fun insertEventData(eventEntity: EventEntity) {
        eventRepository.insertEventData(eventEntity)
    }

    override suspend fun updateEventData(eventEntity: EventEntity) {
        eventRepository.updateEventData(eventEntity)
    }

    override fun getAllEvents(): Flow<List<EventEntity>> {
        return eventRepository.getAllEvents()
    }

    override suspend fun enableAllEvents() {
        return eventRepository.enableAllEvents()
    }

    override suspend fun disableAllEvents() {
        return eventRepository.disableAllEvents()
    }
}