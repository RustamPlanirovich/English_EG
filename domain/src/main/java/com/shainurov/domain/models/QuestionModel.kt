package com.shainurov.domain.models

data class QuestionModel(
    val fileName:String,
    val id: Int,
    var countCorrectAnswers: Int,
    var countIncorrectAnswers: Int,
    var removeFromStudy: Boolean,
    var sentenceInEnglish: String,
    var sentenceInRussian: String,
    var totalCount: Int,
    val transcription: String
)