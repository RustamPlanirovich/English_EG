package com.shainurov.englisheg.di.moduls

import android.content.Context
import com.shainurov.data.datasource.FileDataSource
import com.shainurov.data.network.ApiService
import com.shainurov.data.repository.FileRepositoryImpl
import com.shainurov.domain.repository.FileRepository
import com.shainurov.domain.useCase.*
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
    fun provideFileDataSource(@ApplicationContext context: Context): FileDataSource {
        return FileDataSource(getClient(), context)
    }

    @Provides
    @Singleton
    fun provideFileRepositroty(@ApplicationContext context: Context): FileRepository {
        return FileRepositoryImpl(provideFileDataSource(context))
    }

    @Provides
    fun provideDeleteFileUseCase(@ApplicationContext context: Context):DeleteFileUseCase{
        return DeleteFileUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun provideDeletePlaylistUseCase(@ApplicationContext context: Context):DeletePlaylistUseCase{
        return DeletePlaylistUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun provideGetListOfPlaylistsUseCase(@ApplicationContext context: Context):GetListOfPlaylistsUseCase{
        return GetListOfPlaylistsUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun provideGetListUseCase(@ApplicationContext context: Context):GetListUseCase{
        return GetListUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun provideSaveAllPlaylistsUseCase(@ApplicationContext context: Context):SaveAllPlaylistsUseCase{
        return SaveAllPlaylistsUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun provideSavePlaylistUseCase(@ApplicationContext context: Context):SavePlaylistUseCase{
        return SavePlaylistUseCase(provideFileRepositroty(context))
    }


}