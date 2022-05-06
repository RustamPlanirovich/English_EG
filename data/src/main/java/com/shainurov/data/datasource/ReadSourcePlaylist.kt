package com.shainurov.data.datasource

import android.content.Context
import android.os.Environment
import android.util.Log
import com.shainurov.data.models.Numbers
import com.shainurov.data.network.ApiService
import java.io.File

class ReadSourcePlaylist(private val apiService: ApiService, private val context: Context) {

    private val dataA = ArrayList<Numbers>()


    suspend fun readPlaylists(): ArrayList<Numbers> {
        val responseBody = apiService.getData().body()
        val datas = responseBody?.string()?.split(";")
        if (datas != null) {
            for (i in datas) {

                dataA.add(
                    Numbers(
                        name = i.split(",")[0],
                        url = i.split(",")[1],
                        size = i.split(",")[2],
                        level = i.split(",")[3],
                        File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                            i.split(",")[0].trim()
                        ).exists()
                    )
                )
            }
        }
        return dataA
    }
}