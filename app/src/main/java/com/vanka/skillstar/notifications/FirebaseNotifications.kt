package com.vanka.skillstar.notifications



import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //Add your token in your sharepreferences.
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fcm_token", token).apply()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
    }

    companion object {
        //Whenewer you need FCM token, just call this static method to get it.
        fun getToken(context: Context): String? {
            return context.getSharedPreferences("_", MODE_PRIVATE).getString("fcm_token", "empty")
        }
    }
}