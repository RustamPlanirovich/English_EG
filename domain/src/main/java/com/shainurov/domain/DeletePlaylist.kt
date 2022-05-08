package com.shainurov.domain

class DeletePlaylist(private val deleteFile: DeleteFile) {

    fun execute(filePath: String){
        deleteFile.delete(filePath)
    }
}