package com.shainurov.data.datasource

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.shainurov.data.models.Numbers
import com.shainurov.data.network.ApiService
import java.io.File

class ReadSourcePlaylist(private val apiService: ApiService) {

    private val dataA = ArrayList<Numbers>()


    suspend fun readPlaylists(): ArrayList<Numbers> {
        val responseBody = apiService.getData().body()
        val datas = responseBody?.string()?.split(";")
        if (datas != null) {
            for (i in datas) {

                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                    i.split(",")[0].trim()
                )

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
}