package com.shainurov.data.repository

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shainurov.domain.SaveAllPlaylists
import java.io.File


class SaveAllPlaylistsImpl(context: Context) : SaveAllPlaylists {

    private var dm: DownloadManager? = null


    init {

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
                    Environment.DIRECTORY_DOCUMENTS,
                    "$playlistName".trim()
                )
        )
    }
}