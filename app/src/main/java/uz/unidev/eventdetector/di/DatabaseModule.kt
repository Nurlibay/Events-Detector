package uz.unidev.eventdetector.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.unidev.eventdetector.R
import uz.unidev.eventdetector.data.room.EventDao
import uz.unidev.eventdetector.data.room.EventEntity
import uz.unidev.eventdetector.data.room.EventRoomDatabase
import javax.inject.Singleton

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:30
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): EventRoomDatabase {
        return Room.databaseBuilder(
            context, EventRoomDatabase::class.java, "events_database.db"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                val list = listOf(
                    EventEntity(0, "Screen on", 0, R.drawable.ic_screen_on),
                    EventEntity(0, "Screen off", 0, R.drawable.ic_screen_off),
                    EventEntity(0, "Wifi connected", 0, R.drawable.ic_wifi_on),
                    EventEntity(0, "Wifi disconnected", 0, R.drawable.ic_wifi_off),
                    EventEntity(0, "Bluetooth on", 0, R.drawable.ic_bluetooth_on),
                    EventEntity(0, "Bluetooth off", 0, R.drawable.ic_bluetooth_of),
                    EventEntity(0, "HeadPhones on", 0, R.drawable.ic_headphones_on),
                    EventEntity(0, "HeadPhones off", 0, R.drawable.ic_headphone_off),
                    EventEntity(0, "Plane on", 0, R.drawable.ic_plane_on),
                    EventEntity(0, "Plane off", 0, R.drawable.ic_plane_off),
                    EventEntity(0, "Time changed", 0, R.drawable.time_change),
                    EventEntity(0, "Shut down", 0, R.drawable.shutdown),
                    EventEntity(0, "Full battery", 0, R.drawable.full_battery),
                    EventEntity(0, "Low battery", 0, R.drawable.low_battery),
                    EventEntity(0, "Power connected", 0, R.drawable.phone_charging),
                    EventEntity(0, "Power disconnected", 0, R.drawable.phone_not_charging),
                )

                val dao = provideEventDao(provideDatabase(context))
                CoroutineScope(Dispatchers.IO).launch {
                    for (i in list){
                        dao.insertEvent(i)
                    }
                }
                super.onCreate(db)
            }
        }).build()
    }

    @[Provides Singleton]
    fun provideEventDao(eventRoomDatabase: EventRoomDatabase): EventDao {
        return eventRoomDatabase.eventDao()
    }

}