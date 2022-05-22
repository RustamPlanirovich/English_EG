package com.shainurov.data.datasource.room


import androidx.room.*
import com.shainurov.data.models.Question

@Dao
interface QuestionDao {

    @Query("SELECT * FROM englisGE WHERE fileName=:fileName ")
    fun getAllQuestion(fileName: String): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(question: Question)

    @Query("DELETE FROM englisGE WHERE fileName = :fileName")
    fun delete(vararg fileName: String): Int
}