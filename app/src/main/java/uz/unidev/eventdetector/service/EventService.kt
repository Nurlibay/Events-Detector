package uz.unidev.eventdetector.service

import android.app.*
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import uz.unidev.eventdetector.R
import uz.unidev.eventdetector.presentation.MainActivity
import uz.unidev.eventdetector.receivers.EventBroadcastReceiver


/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 14:12
 */

@AndroidEntryPoint
class EventService : Service() {

    private var eventBroadcast: EventBroadcastReceiver? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        if(eventBroadcast == null){
            eventBroadcast = EventBroadcastReceiver()
        }

        createChannel()

        val id = 123

        val resultIntent = Intent(this, MainActivity::class.java)
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)

        val pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT)
        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT)

        val cancelIntent = Intent(baseContext, EventBroadcastReceiver::class.java).setAction("EXIT")
        val cancelPendingIntent = PendingIntent.getBroadcast(baseContext, 0, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.bell_small)
            setContentText(CONTENT_TEXT)
            setContentIntent(pendingIntent)
            //addAction(R.drawable.ic_clear, "Dismiss", cancelPendingIntent)
            setAutoCancel(true)
            setOngoing(false)
        }.build()

        notification.flags = Notification.FLAG_AUTO_CANCEL

        startForeground(id, notification)

        registerReceiver(eventBroadcast, IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
            addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_DATE_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
            addAction(Intent.ACTION_SHUTDOWN)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_HEADSET_PLUG)
        })
    }

    /**
    private fun pendingIntent(): PendingIntent? {
        val resultIntent = Intent(this, MainActivity::class.java)
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)
        return stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT)
    }
    */

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(eventBroadcast == null){
            eventBroadcast = EventBroadcastReceiver()
        }
        return START_STICKY
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.cancelAll()
        }
    }

    override fun onDestroy() {
        if (eventBroadcast != null) {
            unregisterReceiver(eventBroadcast)
        }
        super.onDestroy()
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val EVENT_DETECTOR = "EVENT_DETECTOR"
        private const val CONTENT_TEXT = "This app listen events from System"
    }
}