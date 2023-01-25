package uz.nurlibaydev.eventdetector.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.nurlibaydev.eventdetector.domain.usecase.EventUseCase
import uz.nurlibaydev.eventdetector.domain.usecase.EventUseCaseImpl

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:42
 */

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindEventUseCase(eventUseCaseImpl: EventUseCaseImpl): EventUseCase

}