package com.shainurov.domain

import com.shainurov.domain.models.PlaylistModel

class GetList(private val getListOfPlaylists: GetListOfPlaylists) {

    suspend fun execute(): ArrayList<PlaylistModel> {
        return getListOfPlaylists.readListPlaylist()
    }
}