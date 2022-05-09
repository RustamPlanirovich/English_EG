package com.shainurov.data.repository

import android.content.ContentResolver
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toUri
import com.shainurov.domain.DeleteFile
import java.io.File

class DeleteFileImpl : DeleteFile {
    override fun delete(filePath: File):Boolean {
       return filePath.absoluteFile.delete()
    }
}
