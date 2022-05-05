package com.shainurov.data.converter

import com.shainurov.data.datasource.ReadSourcePlaylist
import com.shainurov.data.network.ApiService
import com.shainurov.domain.models.PlaylistModel


class ConvertListToDomain(private val apiService: ApiService) {

    suspend fun convert(): ArrayList<PlaylistModel> {
        val result = ReadSourcePlaylist(apiService).readPlaylists()
        val converData = ArrayList<PlaylistModel>()
        for (i in result) {
            converData.add(
                PlaylistModel(
                    name = i.name,
                    url = i.url,
                    size = i.size,
                    level = i.level
                )
            )
        }
        return converData
    }
}