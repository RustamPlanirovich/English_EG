package com.shainurov.domain


import com.shainurov.domain.models.PlaylistModel

interface GetListOfPlaylists {
    suspend fun readListPlaylist(): ArrayList<PlaylistModel>
}