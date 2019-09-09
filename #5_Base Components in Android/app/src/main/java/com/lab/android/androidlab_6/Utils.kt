package com.lab.android.androidlab_6

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.R.id




object Utils {
    val EXTRA_KEY_MESSAGE = "message"
    val REQUEST_CODE = 1
    val CHANNEL_ID ="channel_id"
    val CHANNEL_TITLE ="channel_title"

    fun sendNotification(context: Context, pendingIntent: PendingIntent) {

        val builder = NotificationCompat.Builder(context,CHANNEL_ID)
        builder.setSmallIcon(R.drawable.ic_dialog_alert)
        builder.setContentIntent(pendingIntent)
        builder.setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_dialog_alert))
        builder.setContentTitle("Notifications Title")
        builder.setContentText("Your notification content here.")
        builder.setSubText("Tap to view the website.")

        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = notificationManager?.getNotificationChannel (CHANNEL_ID)
           if( channel == null) {
               channel = NotificationChannel(CHANNEL_ID, CHANNEL_TITLE, NotificationManager.IMPORTANCE_HIGH)
               notificationManager?.createNotificationChannel(channel)
            }
        }

        notificationManager!!.notify(1, builder.build())
    }
}