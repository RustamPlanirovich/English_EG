package com.shainurov.domain.models

import java.io.File

data class PlaylistModel(
    val name: String,
    val url: String,
    val size: String,
    val level: String,
    val download: Boolean,
    val filePath: File
)
