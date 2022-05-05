package com.shainurov.domain

import com.shainurov.domain.models.Numbers

class GetList(private val getListOfPlaylists: GetListOfPlaylists) {

    suspend fun execute(): ArrayList<Numbers> {
        return getListOfPlaylists.readListPlaylist()
    }
}