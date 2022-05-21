package com.shainurov.data.converter

import com.shainurov.data.models.Numbers
import com.shainurov.domain.models.PlaylistModel


fun Numbers.mapToDomain() = PlaylistModel(
        name = name,
        url = url,
        size = size,
        level = level,
        download = downloads,
        filePath = filePath
    )

    fun PlaylistModel.mapToDomain() = Numbers(
        name = name,
        url = url,
        size = size,
        level = level,
        downloads = download,
        filePath = filePath
    )
