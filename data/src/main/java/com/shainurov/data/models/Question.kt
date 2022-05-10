package com.shainurov.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "englisGE")
data class Question(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "countCorrectAnswers")
    var countCorrectAnswers: Int,

    @ColumnInfo(name = "countIncorrectAnswers")
    var countIncorrectAnswers: Int,

    @ColumnInfo(name = "removeFromStudy")
    val removeFromStudy: Boolean,

    @ColumnInfo(name = "sentenceInEnglish")
    val sentenceInEnglish: String,

    @ColumnInfo(name = "sentenceInRussian")
    val sentenceInRussian: String,

    @ColumnInfo(name = "totalCount")
    var totalCount: Int,

    @ColumnInfo(name = "transcription")
    val transcription: String
)

