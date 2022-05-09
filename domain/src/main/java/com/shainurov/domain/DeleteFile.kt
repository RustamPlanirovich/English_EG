package com.shainurov.domain

import java.io.File

interface DeleteFile {
    fun delete(filePath: File):Boolean
}