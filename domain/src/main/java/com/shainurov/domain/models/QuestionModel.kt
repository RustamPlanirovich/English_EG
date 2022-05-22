package com.shainurov.domain.models

data class QuestionModel(
    val fileName:String,
    val id: Int,
    var countCorrectAnswers: Int,
    var countIncorrectAnswers: Int,
    val removeFromStudy: Boolean,
    val sentenceInEnglish: String,
    val sentenceInRussian: String,
    var totalCount: Int,
    val transcription: String
)