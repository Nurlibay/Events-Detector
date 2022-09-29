package uz.unidev.eventdetector.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.unidev.eventdetector.data.room.EventEntity
import uz.unidev.eventdetector.domain.usecase.EventUseCase
import uz.unidev.eventdetector.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val eventUseCase: EventUseCase,
    private val navigator: Navigator
) : MainViewModel, ViewModel() {
    override val allEventData = MutableSharedFlow<List<EventEntity>>()

    override fun itemClick(eventEntity: EventEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            eventUseCase.updateEventData(eventEntity)
        }
    }

    override fun getAllEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            eventUseCase.getAllEvents().collectLatest {
                allEventData.emit(it)
            }
        }
    }

    override fun settingsClicked() {
        TODO("Not yet implemented")
    }
}