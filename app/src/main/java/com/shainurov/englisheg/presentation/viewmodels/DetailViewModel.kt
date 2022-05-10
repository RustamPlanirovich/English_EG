package com.shainurov.englisheg.presentation.viewmodels

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.shainurov.data.datasource.room.repository.Repository
import com.shainurov.data.models.Question
import com.shainurov.data.models.Questions
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: Repository
) : ViewModel() {

    var data = MutableLiveData<List<Question>>()

    fun readDataFromDatabase(){
        viewModelScope.launch (Dispatchers.IO){
            data.postValue(repository.getDatabase().getAllQuestion())
        }
    }


    fun readDataFromJsonFile(path: Uri) {
        val jsonSelectedFile = context.contentResolver.openInputStream(path)
        val inputAsString = jsonSelectedFile?.bufferedReader().use { it?.readText() }
        val gson = Gson()
        val data = gson.fromJson(inputAsString, Questions::class.java)
        for (question in data.questions) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getDatabase().insert(question)
            }
            Log.d("Hell", "$question")
        }
    }
}