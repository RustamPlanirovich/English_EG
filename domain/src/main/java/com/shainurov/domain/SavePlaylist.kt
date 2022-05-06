package com.shainurov.domain

import com.shainurov.domain.models.SaveAllPlaylists

class SavePlaylist(private val saveAllPlaylists: SaveAllPlaylists) {

    fun execute( playlistUrl: String?, playlistName: String?) {
        saveAllPlaylists.download( playlistUrl, playlistName)
    }
}