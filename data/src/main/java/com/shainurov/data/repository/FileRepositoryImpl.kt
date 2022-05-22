package com.shainurov.data.repository

import com.shainurov.data.converter.mapToData
import com.shainurov.data.converter.mapToDomain
import com.shainurov.data.datasource.FileDataSource
import com.shainurov.domain.models.FileList
import com.shainurov.domain.models.PlaylistModel
import com.shainurov.domain.models.QuestionModel
import com.shainurov.domain.repository.FileRepository
import java.io.File

class FileRepositoryImpl(
    private val fileDataSource: FileDataSource
) : FileRepository {

    override fun deletePlaylist(filePath: File): Boolean {
        return fileDataSource.deleteFile(filePath)
    }

    override suspend fun getList(): List<PlaylistModel> {
        return fileDataSource.readPlaylists().map { it.mapToDomain() }
    }


    override suspend fun getListOfPlalist(path: File): List<QuestionModel> {
        return fileDataSource.readDataFromJsonFile(path).map { it.mapToDomain() }
    }


    override fun savePlaylist(playlistUrl: String?, playlistName: String?) {
        fileDataSource.saveAllPlaylist(playlistUrl, playlistName)
    }

    override fun getAllFromDatabase(fileName: String): List<QuestionModel> {
        return fileDataSource.getFromDatabase(fileName).map { it.mapToDomain() }
    }

    override suspend fun getFileList(): List<FileList> {
        return fileDataSource.getListWithAllFiles().map { it.mapToData() }
    }

    override suspend fun insertDatabase(questionModel: QuestionModel) {
        fileDataSource.insertDatabase(questionModel.mapToData())
    }

}