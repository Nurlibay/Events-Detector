package uz.unidev.eventdetector.presentation.main

import kotlinx.coroutines.flow.SharedFlow
import uz.unidev.eventdetector.data.room.EventEntity

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:53
 */

interface MainViewModel {
    val allEventData: SharedFlow<List<EventEntity>>
    fun itemClick(eventEntity: EventEntity)
    fun getAllEvents()
    fun settingsClicked()
}