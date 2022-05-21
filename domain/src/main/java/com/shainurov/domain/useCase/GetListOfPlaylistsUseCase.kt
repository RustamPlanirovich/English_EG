package com.shainurov.domain.useCase


import com.shainurov.domain.models.PlaylistModel
import com.shainurov.domain.repository.FileRepository


class GetListOfPlaylistsUseCase (private val fileRepository: FileRepository) {
    operator fun invoke(): ArrayList<PlaylistModel>{
        return  fileRepository.getListOfPlalist()
    }
}