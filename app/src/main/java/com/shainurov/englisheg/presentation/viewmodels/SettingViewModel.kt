package com.shainurov.englisheg.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shainurov.domain.GetList
import com.shainurov.domain.models.PlaylistModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getList: GetList
) : ViewModel() {

    val data = MutableLiveData<List<PlaylistModel>>()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            data.postValue(getList.execute())
            Log.d("Hello", "${getList.execute()}")
        }
    }

}