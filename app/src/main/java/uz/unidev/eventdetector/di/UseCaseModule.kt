package uz.unidev.eventdetector.di

import androidx.room.Insert
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.unidev.eventdetector.domain.usecase.EventUseCase
import uz.unidev.eventdetector.domain.usecase.EventUseCaseImpl

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:42
 */

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindEventUseCase(eventUseCaseImpl: EventUseCaseImpl): EventUseCase

}