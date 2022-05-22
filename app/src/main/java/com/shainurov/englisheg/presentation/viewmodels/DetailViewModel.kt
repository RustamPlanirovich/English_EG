package com.shainurov.englisheg.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shainurov.data.datasource.room.repository.Repository
import com.shainurov.domain.models.QuestionModel
import com.shainurov.domain.useCase.GetAllFromDatabase
import com.shainurov.domain.useCase.GetListOfPlaylistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getListOfPlaylistsUseCase: GetListOfPlaylistsUseCase,
    private val getAllFromDatabase: GetAllFromDatabase
) : ViewModel() {

    var data = MutableLiveData<List<QuestionModel>>()


    fun getListOfPlaylist(path: File) {
        viewModelScope.launch(Dispatchers.IO) { getListOfPlaylistsUseCase.invoke(path) }

    }

    fun readDataFromDatabase(fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(getAllFromDatabase.invoke(fileName = fileName))
        }
    }


//    fun readDataFromJsonFile(path: Uri) {
//        val jsonSelectedFile = context.contentResolver.openInputStream(path)
//        val inputAsString = jsonSelectedFile?.bufferedReader().use { it?.readText() }
//        val gson = Gson()
//        val data = gson.fromJson(inputAsString, Questions::class.java)
//        for (question in data.questions) {
//            viewModelScope.launch(Dispatchers.IO) {
//                repository.getDatabase().insert(question)
//            }
//            Log.d("Hell", "$question")
//        }
//    }
}