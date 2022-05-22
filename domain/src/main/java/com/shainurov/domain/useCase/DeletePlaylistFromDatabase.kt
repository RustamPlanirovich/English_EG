package com.shainurov.domain.useCase

import com.shainurov.domain.repository.FileRepository

class DeletePlaylistFromDatabase(private val fileRepository: FileRepository) {
    suspend operator fun invoke(fileName: String): Int {
        return fileRepository.deleteAll(fileName = fileName)
    }
}