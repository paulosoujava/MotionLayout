package com.paulo.motionanimation.deepLink

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.paulo.motionanimation.R

//para testar:
// adb shell am start -W -a android.intent.action.VIEW -d "https://composables.com/blog/naviga"
@Composable
fun MyDeepLink(context: Context) {
    val navController = rememberNavController()

    NavHost(navController, "home") {
       composable(
           "home"
       ){
           Button(onClick = {
               createNotification(context)
           }) {
               Text("Notification")
           }
       }


        composable(
            "details?article={argument}",
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://composables.com/blog/{argument}"
            }),
        ) { backStackEntry ->
            val article = backStackEntry.arguments?.getString("argument")
            Text("Showing /$article")
        }
    }

}



fun createNotification(context: Context) {
    val channelId = "my_channel_id"
    val notificationId = 1

    // Cria um canal de notificação (necessário para Android 8.0 e posterior)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "My Channel"
        val descriptionText = "Descrição do canal"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            ContextCompat.getSystemService(context, NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://composables.com/blog/navigation-tutorial")
    )
    val activity = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


    // Cria a notificação
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.amd_icon)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentTitle("🧭 Navigation in Jetpack Compose")
        .setContentText("Everything you need to know")
        .setContentIntent(activity)
        .build()

    val notificationManagerCompat = NotificationManagerCompat.from(context)
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return
    }
    notificationManagerCompat.notify(notificationId, builder)
}



