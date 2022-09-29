package uz.unidev.eventdetector.receivers

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.speech.tts.TextToSpeech
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.unidev.eventdetector.data.room.EventDao
import java.util.*
import javax.inject.Inject


/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 14:14
 */

@AndroidEntryPoint
class EventBroadcastReceiver : BroadcastReceiver() {

    private var tts: TextToSpeech? = null

    @Inject
    lateinit var dao: EventDao

    override fun onReceive(context: Context?, intent: Intent?) {
        if (tts == null) {
            tts = TextToSpeech(context!!) { status ->
                if (status != TextToSpeech.ERROR) {
                    tts!!.language = Locale.US
                }
            }
        }
        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> {
                event(1)
            }
            Intent.ACTION_SCREEN_OFF -> {
                event(2)
            }
            WifiManager.NETWORK_STATE_CHANGED_ACTION -> {
                val wifiState = intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
                if (wifiState?.isConnected!!) event(3)
                else event(4)
            }
            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)) {
                    BluetoothAdapter.STATE_ON -> {
                        event(5)
                    }
                    BluetoothAdapter.STATE_OFF -> {
                        event(6)
                    }
                }
            }
            Intent.ACTION_HEADSET_PLUG -> {
                val headsetPlugOn = intent.getIntExtra("state", 0)
                if (headsetPlugOn == 1) event(7)
                else event(8)
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val turnedOn = intent.getBooleanExtra("state", false)
                if (turnedOn) event(9)
                else event(10)
            }
            Intent.ACTION_TIME_CHANGED -> {
                event(11)
            }
            Intent.ACTION_SHUTDOWN -> {
                event(12)
            }
            Intent.ACTION_BATTERY_OKAY -> {
                event(13)
            }
            Intent.ACTION_BATTERY_LOW -> {
                event(14)
            }
            Intent.ACTION_POWER_CONNECTED -> {
                event(15)
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                event(16)
            }
            else -> {}
        }
    }

    private fun event(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = dao.getEventById(id)
            if (data.status == 1) {
                speak(data.name)
            }
        }
    }

    private fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}