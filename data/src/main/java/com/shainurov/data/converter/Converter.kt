package com.shainurov.data.converter

import com.shainurov.data.models.FileListData
import com.shainurov.data.models.Numbers
import com.shainurov.data.models.Question
import com.shainurov.domain.models.FileList
import com.shainurov.domain.models.PlaylistModel
import com.shainurov.domain.models.QuestionModel


fun Numbers.mapToDomain() = PlaylistModel(
    name = name,
    url = url,
    size = size,
    level = level,
    download = downloads,
    filePath = filePath
)

fun PlaylistModel.mapToDomain() = Numbers(
    name = name,
    url = url,
    size = size,
    level = level,
    downloads = download,
    filePath = filePath
)

fun Question.mapToDomain() = QuestionModel(
    fileName = fileName,
    id = id,
    countCorrectAnswers = countCorrectAnswers,
    countIncorrectAnswers = countIncorrectAnswers,
    removeFromStudy = removeFromStudy,
    sentenceInEnglish = sentenceInEnglish,
    sentenceInRussian = sentenceInRussian,
    totalCount = totalCount,
    transcription = transcription
)

fun QuestionModel.mapToData() = Question(
    fileName = fileName,
    id = id,
    countCorrectAnswers = countCorrectAnswers,
    countIncorrectAnswers = countIncorrectAnswers,
    removeFromStudy = removeFromStudy,
    sentenceInEnglish = sentenceInEnglish,
    sentenceInRussian = sentenceInRussian,
    totalCount = totalCount,
    transcription = transcription
)

fun FileList.mapToData() = FileListData(
    fileName = fileName,
)
fun FileListData.mapToData() = FileList(
    fileName = fileName,
)