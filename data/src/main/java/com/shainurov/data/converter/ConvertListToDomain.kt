package com.shainurov.data.converter

import com.shainurov.data.datasource.ReadSourcePlaylist
import com.shainurov.data.models.Numbers
import com.shainurov.data.network.ApiService


class ConvertListToDomain(private val apiService: ApiService) {

    suspend fun convert():ArrayList<com.shainurov.domain.models.Numbers> {
       val result = ReadSourcePlaylist(apiService).readPlaylists()
        return result as ArrayList<com.shainurov.domain.models.Numbers>
    }
}