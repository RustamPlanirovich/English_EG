package com.shainurov.englisheg.di.moduls

import android.content.Context
import com.shainurov.data.network.ApiService
import com.shainurov.data.repository.DeleteFileImpl
import com.shainurov.data.repository.GetListOfPlaylistsImpl
import com.shainurov.data.repository.SaveAllPlaylistsImpl
import com.shainurov.domain.DeleteFile
import com.shainurov.domain.DeletePlaylist
import com.shainurov.domain.GetList
import com.shainurov.domain.SavePlaylist
import com.shainurov.englisheg.presentation.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun provideUrl(): String {
        return BASE_URL
    }

    @Provides
    @Singleton
    fun getClient(): ApiService {
        return Retrofit.Builder().baseUrl(provideUrl())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun getList(getListOfPlaylists: GetListOfPlaylistsImpl): GetList {
        return GetList(getListOfPlaylists)
    }

    @Provides
    @Singleton
    fun getListOfPlaylists(
        getClient: ApiService,
        @ApplicationContext context: Context
    ): GetListOfPlaylistsImpl {
        return GetListOfPlaylistsImpl(getClient, context)
    }

    @Provides
    fun savePlaylist(saveAllPlaylists: SaveAllPlaylistsImpl): SavePlaylist {
        return SavePlaylist(saveAllPlaylists)
    }

    @Provides
    fun saveAllPlaylists(
        @ApplicationContext context: Context,
    ): SaveAllPlaylistsImpl {
        return SaveAllPlaylistsImpl(context)
    }

    @Provides
    fun deletePlaylist(deleteFile: DeleteFileImpl): DeletePlaylist {
        return DeletePlaylist(deleteFile)
    }
    @Provides
    fun deleteFile():DeleteFileImpl{
        return DeleteFileImpl()
    }


}