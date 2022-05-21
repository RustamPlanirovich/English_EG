package com.shainurov.domain.useCase

import com.shainurov.domain.repository.FileRepository


class SavePlaylistUseCase(private val fileRepository: FileRepository) {

    operator fun invoke(playlistUrl: String?, playlistName: String?){
        fileRepository.savePlaylist(playlistUrl,playlistName)
    }
}