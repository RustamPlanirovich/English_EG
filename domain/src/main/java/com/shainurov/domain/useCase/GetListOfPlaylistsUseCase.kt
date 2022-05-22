package com.shainurov.domain.useCase


import com.shainurov.domain.models.QuestionModel
import com.shainurov.domain.repository.FileRepository
import java.io.File


class GetListOfPlaylistsUseCase(private val fileRepository: FileRepository) {
    suspend operator fun invoke(path: File, name: String): List<QuestionModel> {
        return fileRepository.getListOfPlalist(path, name)
    }
}