package com.example.treasurehunt

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "GeofenceReceiver"
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != MainActivity.ACTION_GEOFENCE_EVENT) {
            Log.e(TAG, "Not Triggered by a geofencing event. Abort!")
            return
        }
        val geofencingEvent = GeofencingEvent.fromIntent(intent) ?: return
        if (geofencingEvent.hasError()) {
            val errorMessage = errorMessage(context, geofencingEvent.errorCode)
            Log.e(TAG, errorMessage)
            return
        }

        //if (geofencingEvent.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            Log.v(TAG, context.getString(R.string.geofence_entered))
            val fenceId = when {
                geofencingEvent.triggeringGeofences?.isNotEmpty() == true ->
                    geofencingEvent.triggeringGeofences?.get(0)?.requestId

                else -> {
                    Log.e(TAG, "No Geofence Trigger Found! Abort mission!")
                    return
                }
            }
            val foundIndex = GeofencingConstants.LANDMARK_DATA.indexOfFirst {
                it.id == fenceId
            }
            if (-1 == foundIndex) {
                Log.e(TAG, "Unknown Geofence: Abort Mission")
                return
            }

            val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
            ) as NotificationManager

            if (geofencingEvent.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                notificationManager.sendGeofenceEnteredNotification(
                    context, foundIndex
                )
            } else {
                notificationManager.sendGeofenceExitedNotification(
                    context, foundIndex
                )
            }
        //}
    }
}