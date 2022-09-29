package uz.unidev.eventdetector.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.unidev.eventdetector.data.room.EventDao
import javax.inject.Inject

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 15:17
 */

@HiltAndroidApp
class EventApp : Application()