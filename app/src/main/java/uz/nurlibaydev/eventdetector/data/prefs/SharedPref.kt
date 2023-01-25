package uz.nurlibaydev.eventdetector.data.prefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.nurlibaydev.eventdetector.utils.SharedPreference
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 14:57
 */

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context: Context) : SharedPreference(
    context,
    context.getSharedPreferences("MY_SHARED_PREFERENCE", Context.MODE_PRIVATE)
) {
    val screenOn: Boolean by Booleans(false)

    val screenOf: Boolean by Booleans(false)

    val wifiOn: Boolean by Booleans(false)

    val wifiOff: Boolean by Booleans(false)

    val bluetoothOn: Boolean by Booleans(false)

    val bluetoothOff: Boolean by Booleans(false)

    val headPhonesOn: Boolean by Booleans(false)

    val headPhonesOff: Boolean by Booleans(false)

    val planeOn: Boolean by Booleans(false)

    val planeOff: Boolean by Booleans(false)

    val changedTime: Boolean by Booleans(false)

    val shutDown: Boolean by Booleans(false)

    val lowBattery: Boolean by Booleans(false)

    val fullBattery: Boolean by Booleans(false)
}