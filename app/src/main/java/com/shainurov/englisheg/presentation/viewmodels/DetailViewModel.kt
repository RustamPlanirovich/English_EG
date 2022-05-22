package com.shainurov.englisheg.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val mutableSelectedItem = MutableLiveData<QuestionModel>()


    fun getListOfPlaylist(path: File, name: String) {
        viewModelScope.launch(Dispatchers.IO) { getListOfPlaylistsUseCase.invoke(path, name) }

    }

    fun readDataFromDatabase(fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(getAllFromDatabase.invoke(fileName = fileName))
        }
    }

}