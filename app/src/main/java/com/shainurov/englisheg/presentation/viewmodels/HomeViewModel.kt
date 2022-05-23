package com.shainurov.englisheg.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shainurov.domain.models.FileList
import com.shainurov.domain.useCase.GetFileListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getFileListUseCase: GetFileListUseCase) :
    ViewModel() {

    val data:MutableLiveData<List<FileList>> = MutableLiveData()
    val selectFile = MutableLiveData<FileList>()



    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            data.postValue(getFileListUseCase.invoke().let { it })
        }
    }
}