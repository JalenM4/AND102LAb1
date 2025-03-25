package com.codepath.bitfitp2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        sendNotification()
        return Result.success()
    }

    private fun sendNotification() {
        val builder = NotificationCompat.Builder(applicationContext, "bitfit_channel")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Daily Reminder")
            .setContentText("Don't forget to log your food today!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val manager = applicationContext.getSystemService(NotificationManager::class.java)
        manager.notify(1, builder.build())
    }
}
