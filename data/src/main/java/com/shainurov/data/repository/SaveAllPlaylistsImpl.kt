package com.shainurov.data.repository

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shainurov.domain.SaveAllPlaylists

class SaveAllPlaylistsImpl(context: Context) : SaveAllPlaylists {

    private var dm: DownloadManager? = null


    init {
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.action
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE == action) {
                    Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show()
                }
            }
        }


        context.registerReceiver(
            broadcastReceiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
        dm = context.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
    }


    override fun download(playlistUrl: String?, playlistName: String?) {
        dm?.enqueue(
            DownloadManager.Request(Uri.parse(playlistUrl))
                .setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_MOBILE or
                            DownloadManager.Request.NETWORK_WIFI
                )
                .setTitle("$playlistName")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DCIM,
                    "$playlistName".trim()
                )
        )
    }
}