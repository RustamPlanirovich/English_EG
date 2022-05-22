package com.shainurov.domain.repository

import com.shainurov.domain.models.FileList
import com.shainurov.domain.models.PlaylistModel
import com.shainurov.domain.models.QuestionModel

import java.io.File

interface FileRepository {
    fun deletePlaylist(filePath: File): Boolean
    suspend fun getList(): List<PlaylistModel>
    suspend fun getListOfPlalist(path: File, name: String): List<QuestionModel>
    fun savePlaylist(playlistUrl: String?, playlistName: String?)
    fun getAllFromDatabase(fileName: String): List<QuestionModel>
    suspend fun getFileList(): List<FileList>
    suspend fun insertDatabase(questionModel: QuestionModel)
    suspend fun deleteAll(fileName: String):Int
}