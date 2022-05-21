package com.shainurov.domain.useCase

import com.shainurov.domain.repository.FileRepository


class SaveAllPlaylistsUseCase (private val fileRepository: FileRepository) {
    operator fun invoke(playlistUrl: String?, playlistName: String?){
        fileRepository.download(playlistUrl, playlistName)
    }
}