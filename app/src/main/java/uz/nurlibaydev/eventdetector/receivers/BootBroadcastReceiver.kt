package uz.nurlibaydev.eventdetector.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import uz.nurlibaydev.eventdetector.service.EventService

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 14:28
 */

class BootBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(Intent(context, EventService::class.java))
    }
}