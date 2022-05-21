package com.shainurov.data.datasource

import android.R
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shainurov.data.models.Numbers
import com.shainurov.data.network.ApiService
import java.io.File
import java.io.IOException
import java.nio.file.Files

class FileDataSource(private val apiService: ApiService, private val context: Context) {



    suspend fun readPlaylists(): ArrayList<Numbers> {
        val dataA = ArrayList<Numbers>()
        val responseBody = apiService.getData().body()
        val datas = responseBody?.string()?.split(";")
        if (datas != null) {
            for (i in datas) {

                val z = context.getExternalFilesDir("DATA")
                val file = File(
                    z,
                    i.split(",")[0].trim() //название файла
                )
                Log.d("Hell", z.toString())

                dataA.add(
                    Numbers(
                        name = i.split(",")[0],
                        url = i.split(",")[1],
                        size = i.split(",")[2],
                        level = i.split(",")[3],
                        downloads = file.exists(),
                        filePath = file
                    )
                )
            }
        }
        return dataA
    }

    fun saveAllPlaylist(playlistUrl: String?, playlistName: String?) {
        var dm = context.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(
            DownloadManager.Request(Uri.parse(playlistUrl))
                .setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_MOBILE or
                            DownloadManager.Request.NETWORK_WIFI
                )
                .setTitle("$playlistName")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalFilesDir(
                    context,
                    "DATA",
                    "$playlistName".trim()//название файла
                )
        )
    }

    fun deleteFile(filePath: File): Boolean {
        try {
            Files.delete(filePath.absoluteFile.toPath())
        } catch (x: NoSuchFileException) {
            System.err.format("%s: no such" + " file or directory%n", R.attr.path)
        } catch (x: IOException) {
            // File permission problems are caught here.
            System.err.println(x)
            Log.d("Hell", "$x")
        }
        return true
    }
}