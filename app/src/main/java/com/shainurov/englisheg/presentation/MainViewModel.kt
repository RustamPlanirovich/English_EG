package com.shainurov.englisheg.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shainurov.domain.GetList
import com.shainurov.domain.models.Numbers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getList: GetList
) : ViewModel() {

    val data = MutableLiveData<List<Numbers>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(getList.execute())
        }
    }
}