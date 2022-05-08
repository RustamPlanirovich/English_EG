package com.shainurov.domain

class SavePlaylist(private val saveAllPlaylists: SaveAllPlaylists) {

    fun execute( playlistUrl: String?, playlistName: String?) {
        saveAllPlaylists.download( playlistUrl, playlistName)
    }
}