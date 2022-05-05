package com.shainurov.englisheg.di.moduls

import com.shainurov.data.network.ApiService
import com.shainurov.data.repository.GetListOfPlaylistsImpl
import com.shainurov.domain.GetList
import com.shainurov.domain.GetListOfPlaylists
import com.shainurov.englisheg.presentation.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun getListOfPlaylists(getClient: ApiService): GetListOfPlaylistsImpl {
        return GetListOfPlaylistsImpl(getClient)
    }
}