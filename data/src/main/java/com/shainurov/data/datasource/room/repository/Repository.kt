package com.shainurov.data.datasource.room.repository

import com.shainurov.data.datasource.room.AppDatabase
import javax.inject.Inject


class Repository @Inject constructor(private val db: AppDatabase){
    fun getDatabase() = db.dao()
}