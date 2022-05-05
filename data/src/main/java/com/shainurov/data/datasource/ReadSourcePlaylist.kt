package com.shainurov.data.datasource

import com.shainurov.data.models.Numbers
import com.shainurov.data.network.ApiService

class ReadSourcePlaylist(private val apiService: ApiService) {

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
                        level = i.split(",")[3]
                    )
                )
            }
        }
        return dataA
    }
}