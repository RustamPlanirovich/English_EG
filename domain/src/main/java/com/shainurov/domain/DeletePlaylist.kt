package com.shainurov.domain

import java.io.File

class DeletePlaylist(private val deleteFile: DeleteFile) {

    fun execute(filePath: File):Boolean{
        return deleteFile.delete(filePath)
    }
}