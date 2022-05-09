package com.shainurov.data.models

import java.io.File

data class Numbers(
    val name: String,
    val url: String,
    val size: String,
    val level: String,
    val downloads: Boolean,
    val filePath: File
)
