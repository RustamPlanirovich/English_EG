package com.shainurov.domain.useCase

import com.shainurov.domain.models.QuestionModel
import com.shainurov.domain.repository.FileRepository

class GetAllFromDatabase(private val fileRepository: FileRepository) {
    operator fun invoke(fileName: String): List<QuestionModel> {
        return fileRepository.getAllFromDatabase(fileName)
    }
}



