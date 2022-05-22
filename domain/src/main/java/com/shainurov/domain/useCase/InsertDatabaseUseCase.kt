package com.shainurov.domain.useCase

import com.shainurov.domain.models.QuestionModel
import com.shainurov.domain.repository.FileRepository

class InsertDatabaseUseCase(private val fileRepository: FileRepository) {
    suspend operator fun invoke(questionModel: QuestionModel){
        fileRepository.insertDatabase(questionModel)
    }
}