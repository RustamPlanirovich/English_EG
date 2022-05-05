package com.shainurov.domain


import com.shainurov.domain.models.Numbers

interface GetListOfPlaylists {
    suspend fun readListPlaylist(): ArrayList<Numbers>
}