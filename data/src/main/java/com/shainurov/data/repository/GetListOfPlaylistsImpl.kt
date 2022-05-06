package com.shainurov.data.repository

import android.content.Context
import com.shainurov.data.converter.ConvertListToDomain
import com.shainurov.data.network.ApiService
import com.shainurov.domain.GetListOfPlaylists


class GetListOfPlaylistsImpl(private val apiService: ApiService, private val context: Context) : GetListOfPlaylists {

    override suspend fun readListPlaylist(): ArrayList<com.shainurov.domain.models.PlaylistModel> {
        return ConvertListToDomain(apiService, context).convert()
    }
}