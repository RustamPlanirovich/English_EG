package com.shainurov.englisheg.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shainurov.domain.models.QuestionModel
import com.shainurov.domain.useCase.GetAllFromDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor(
    private val getAllFromDatabase: GetAllFromDatabase
): ViewModel() {

    val data = MutableLiveData<List<QuestionModel>>()


    fun getData(fileName: String){
        viewModelScope.launch (Dispatchers.IO) {
            data.postValue(getAllFromDatabase.invoke(fileName))
        }
    }
}