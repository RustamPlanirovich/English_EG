package com.shainurov.englisheg.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shainurov.domain.models.PlaylistModel
import com.shainurov.domain.useCase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase,
    private val deleteFileUseCase: DeleteFileUseCase,
    private val saveAllPlaylistsUseCase: SaveAllPlaylistsUseCase,
    private val getListOfPlaylistsUseCase: GetListOfPlaylistsUseCase,
    private val savePlaylistUseCase: SavePlaylistUseCase,
    private val deletePlaylistUseCase: DeletePlaylistUseCase
) : ViewModel() {

    val data = MutableLiveData<List<PlaylistModel>>()
    val deleted = MutableLiveData<Boolean>()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(getListUseCase.invoke())
        }
    }

    fun savePlaylist(playlistUrl: String, playlistName: String) {
        savePlaylistUseCase(
            playlistUrl,
            playlistName
        )
    }

    fun deletePlaylisyt(filePath: File) {
        deleted.value = deletePlaylistUseCase(filePath)
    }

}