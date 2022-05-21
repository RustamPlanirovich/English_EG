package com.shainurov.domain.useCase

import com.shainurov.domain.models.PlaylistModel
import com.shainurov.domain.repository.FileRepository


class GetListUseCase (private val fileRepository: FileRepository) {

    suspend operator fun invoke(): List<PlaylistModel> {
        return fileRepository.getList()
    }

}