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
import uz.unidev.eventdetector.data.room.EventDao
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
        )
            .createFromAsset("events_table.db")
            .build()

        /**
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    val list = listOf(
                        "Screen on",
                        "Screen off",
                        "Wifi connected",
                        "Wifi disconnected",
                        "Bluetooth on",
                        "Bluetooth off",
                        "HeadPhones on",
                        "HeadPhones off",
                        "Plane on",
                        "Plane off",
                        "Time changed",
                        "Shut down",
                        "Full battery",
                        "Low battery",
                        "Power connected",
                        "Power disconnected"
                    )

                    val dao = provideEventDao(provideDatabase(context))
                    CoroutineScope(Dispatchers.IO).launch {
                        for (i in list) {
                            dao.insertEvent(EventEntity(0, i, 0))
                        }
                    }
                    super.onCreate(db)
                }
            }) */
    }

    @[Provides Singleton]
    fun provideEventDao(eventRoomDatabase: EventRoomDatabase): EventDao {
        return eventRoomDatabase.eventDao()
    }

}