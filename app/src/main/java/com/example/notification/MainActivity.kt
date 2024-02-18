package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var mychannelid="mychannel1011"
    lateinit var builder: Notification.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent= Intent(applicationContext,MainActivity2::class.java)
        var pendingIntent=PendingIntent.getActivity(this,112,intent,PendingIntent.FLAG_IMMUTABLE)

        var btn=findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            var nm=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
                var noti_channel=NotificationChannel(mychannelid,"my channel description",NotificationManager.IMPORTANCE_HIGH)
                //extra stuff
                noti_channel.enableLights(true)
                noti_channel.enableVibration(true)
                noti_channel.lightColor=Color.BLUE

                //we need to create notification channel
                nm.createNotificationChannel(noti_channel)

                //now we need a builder to call notification

                builder=Notification.Builder(this,mychannelid)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("my notification")
                    .setContentText("hello shivam")
                //FOR PEDNING INTENT ,IE OPEN ONLY WHEN NOTIFICATION IS CLICKED
                    .setContentIntent(pendingIntent)
                nm.notify(111,builder.build())



            }
            else
            {

                //copy if below oreo
                builder=Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("my notification")
                    .setContentText("hello shivam")
                    .setContentIntent(pendingIntent)
                nm.notify(111,builder.build())


            }
        }

    }
}