package com.shainurov.data.datasource.room


import androidx.room.*
import com.shainurov.data.models.Question

@Dao
interface QuestionDao {

    @Query("SELECT * FROM englisGE")
    fun getAllQuestion(): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(question: Question)
}