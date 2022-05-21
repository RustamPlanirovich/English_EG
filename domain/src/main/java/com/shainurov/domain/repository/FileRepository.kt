package com.shainurov.domain.repository

import com.shainurov.domain.useCase.SaveAllPlaylistsUseCase
import com.shainurov.domain.models.PlaylistModel
import java.io.File

interface FileRepository {
    fun deleteFile(filePath: File): Boolean
    fun deletePlaylist(filePath: File): Boolean
    suspend fun getList(): List<PlaylistModel>
    fun getListOfPlalist(): ArrayList<PlaylistModel>
    fun saveAllPlaylist(playlistUrl: String?, playlistName: String?): SaveAllPlaylistsUseCase
    fun savePlaylist(playlistUrl: String?, playlistName: String?)
    fun download(playlistUrl: String?, playlistName: String?)
}