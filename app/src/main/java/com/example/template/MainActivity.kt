package com.example.template

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.template.Navigation.NavigationGraph
import com.example.template.screens.HomeScreen
import com.example.template.screens.components.bottomNav.Screen
import com.example.template.ui.theme.TemplateTheme
import com.example.template.viewModel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = Companion.NOTIFICATION_CHANNEL_ID
            val channelName = "Hii"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_LOW)
            )
        }
        intent.extras?.let {
            for (key in it.keySet()) {
                val value = intent.extras?.get(key)
                Timber.tag(TAG).d("Key:  $key   Value:   $value")
            }
        }

        Firebase.messaging.subscribeToTopic("general")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Failed"
                }
                Timber.tag(TAG).d(msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
        setContent {
            TemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.surface
                ) {
                  HomeScreen()

                }
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
        const val NOTIFICATION_CHANNEL_ID = BuildConfig.APPLICATION_ID + ".channel"
    }

}











