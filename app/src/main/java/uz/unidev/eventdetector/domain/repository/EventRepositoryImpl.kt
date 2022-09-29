package uz.unidev.eventdetector.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.unidev.eventdetector.data.room.EventDao
import uz.unidev.eventdetector.data.room.EventEntity
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDao: EventDao
) : EventRepository {
    override suspend fun insertEventData(eventEntity: EventEntity) {
        return eventDao.insertEvent(eventEntity)
    }

    override suspend fun updateEventData(eventEntity: EventEntity) {
        return eventDao.updateEvent(eventEntity)
    }

    override fun getAllEvents(): Flow<List<EventEntity>> {
        return eventDao.getAllEvents()
    }

    override suspend fun enableAllEvents() {
        return eventDao.enableAllEvents()
    }

    override suspend fun disableAllEvents() {
        return eventDao.disableAllEvents()
    }
}