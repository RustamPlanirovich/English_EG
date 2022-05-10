package com.shainurov.data.datasource.room


import androidx.room.*
import com.shainurov.data.models.Question

@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): QuestionDao
}