package com.shainurov.englisheg.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shainurov.domain.DeletePlaylist
import com.shainurov.domain.GetList
import com.shainurov.domain.SavePlaylist
import com.shainurov.domain.models.PlaylistModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getList: GetList,
    private val savePlaylist: SavePlaylist,
    private val deletePlaylist: DeletePlaylist
) : ViewModel() {

    val data = MutableLiveData<List<PlaylistModel>>()
    val deleted = MutableLiveData<Boolean>()
    val sel = MutableLiveData<PlaylistModel>()


    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(getList.execute())
        }
    }

    fun savePlaylist(playlistUrl: String, playlistName: String) {
        savePlaylist.execute(
            playlistUrl,
            playlistName
        )
    }

    fun deletePlaylisyt(filePath: File) {
        deleted.value = deletePlaylist.execute(filePath)
    }

}