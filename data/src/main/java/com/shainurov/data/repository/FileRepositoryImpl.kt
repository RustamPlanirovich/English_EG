package com.shainurov.data.repository

import android.content.Context
import com.shainurov.data.converter.mapToDomain
import com.shainurov.data.datasource.FileDataSource
import com.shainurov.domain.models.PlaylistModel
import com.shainurov.domain.repository.FileRepository
import com.shainurov.domain.useCase.SaveAllPlaylistsUseCase
import java.io.File

class FileRepositoryImpl(
    private val fileDataSource: FileDataSource
) : FileRepository {
    override fun deleteFile(filePath: File): Boolean {
        return fileDataSource.deleteFile(filePath)
    }

    override fun deletePlaylist(filePath: File): Boolean {
        return fileDataSource.deleteFile(filePath)
    }

    override suspend fun getList(): List<PlaylistModel> {
        return fileDataSource.readPlaylists().map { it.mapToDomain() }
    }

    override fun getListOfPlalist(): ArrayList<PlaylistModel> {
        TODO("Not yet implemented")
    }

    override fun saveAllPlaylist(
        playlistUrl: String?,
        playlistName: String?
    ): SaveAllPlaylistsUseCase {
        TODO("Not yet implemented")
    }

    override fun savePlaylist(playlistUrl: String?, playlistName: String?) {
        fileDataSource.saveAllPlaylist(playlistUrl, playlistName)
    }

    override fun download(playlistUrl: String?, playlistName: String?) {
        TODO("Not yet implemented")
    }

}