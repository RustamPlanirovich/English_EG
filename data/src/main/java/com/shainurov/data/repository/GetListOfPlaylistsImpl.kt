package com.shainurov.data.repository

import com.shainurov.data.converter.ConvertListToDomain
import com.shainurov.data.network.ApiService
import com.shainurov.domain.GetListOfPlaylists


class GetListOfPlaylistsImpl(private val apiService: ApiService) : GetListOfPlaylists {

    override suspend fun readListPlaylist(): ArrayList<com.shainurov.domain.models.PlaylistModel> {
        return ConvertListToDomain(apiService).convert()
    }
}