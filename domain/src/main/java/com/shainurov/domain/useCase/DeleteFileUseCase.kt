package com.shainurov.domain.useCase

import com.shainurov.domain.repository.FileRepository
import java.io.File



class DeleteFileUseCase (private val fileRepository: FileRepository) {

    operator fun invoke(filePath: File): Boolean {
        return fileRepository.deleteFile(filePath)
    }

}