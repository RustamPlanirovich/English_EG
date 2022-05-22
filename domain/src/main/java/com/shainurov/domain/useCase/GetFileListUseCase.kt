package com.shainurov.domain.useCase

import com.shainurov.domain.models.FileList
import com.shainurov.domain.repository.FileRepository

class GetFileListUseCase(private val fileRepository: FileRepository) {
    suspend operator fun invoke(): List<FileList> {
        return fileRepository.getFileList()
    }
}