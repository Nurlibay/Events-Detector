package uz.nurlibaydev.eventdetector.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nurlibaydev.eventdetector.domain.repository.EventRepository
import uz.nurlibaydev.eventdetector.domain.repository.EventRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindEventRepository(eventRepositoryImpl: EventRepositoryImpl): EventRepository

}