package com.shainurov.data.repository

import android.os.Environment
import android.util.Log
import com.shainurov.domain.DeleteFile
import java.io.File

class DeleteFileImpl : DeleteFile {
    override fun delete(filePath: String) {

        val file = File(filePath.trim())

        Log.d("Hell", "${file.exists()}")
        file.delete()
        Log.d("Hell", "${file.delete()}")
        Log.d("Hell", "${file}")
    }
}