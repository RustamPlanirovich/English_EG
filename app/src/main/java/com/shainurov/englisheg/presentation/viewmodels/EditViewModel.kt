package com.shainurov.englisheg.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shainurov.data.models.Question
import com.shainurov.domain.models.QuestionModel
import com.shainurov.domain.useCase.InsertDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val insertDatabaseUseCase: InsertDatabaseUseCase) :
    ViewModel() {

    fun update(question: QuestionModel) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDatabaseUseCase.invoke(question)
        }
    }
}