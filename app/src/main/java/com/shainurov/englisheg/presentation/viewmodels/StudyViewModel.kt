package com.shainurov.englisheg.presentation.viewmodels

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shainurov.domain.models.QuestionModel
import com.shainurov.domain.useCase.GetAllFromDatabase
import com.shainurov.domain.useCase.InsertDatabaseUseCase
import com.shainurov.englisheg.presentation.utils.LoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor(
    private val getAllFromDatabase: GetAllFromDatabase,
    private val insertDatabaseUseCase: InsertDatabaseUseCase
) : ViewModel() {

    val data = MutableLiveData<List<QuestionModel>>()
    val num = MutableLiveData<Int>(0)
    val currentId = MutableLiveData<String>()

    private val _viewState = MutableStateFlow<LoginUIState>(LoginUIState.Empty)
    val viewUIState: StateFlow<LoginUIState> = _viewState

    val mutableSelectedItem = MutableLiveData<QuestionModel>()

    private var coun: Int = 0


    fun getData(fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(getAllFromDatabase.invoke(fileName))
        }
    }

    fun insert(questionModel: QuestionModel) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDatabaseUseCase.invoke(questionModel)
        }
    }

    fun startTimeCounter() {
        coun = 10
        object : CountDownTimer(11000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _viewState.value = LoginUIState.Loading("$coun")
                coun--
            }

            override fun onFinish() {
                _viewState.value = LoginUIState.Done("close")
            }
        }.start()
    }

    fun random() {
        val start = 0
        val end = data.value?.size
        require(!(start > end!! || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        num.value = Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    fun currentId() {
        currentId.value = "${num.value!!.toInt() + 1}/${data.value?.size}"
    }

}