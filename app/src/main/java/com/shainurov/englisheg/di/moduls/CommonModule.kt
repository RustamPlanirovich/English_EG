package com.shainurov.englisheg.di.moduls

import android.content.Context
import androidx.room.Room
import com.shainurov.data.datasource.FileDataSource
import com.shainurov.data.datasource.room.AppDatabase
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
    fun provideRoom(@ApplicationContext applicationContext: Context): AppDatabase {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "englisGE"
        ).build()
        return db
    }


    @Provides
    @Singleton
    fun provideFileDataSource(@ApplicationContext context: Context): FileDataSource {
        return FileDataSource(getClient(), context, provideRoom(context))
    }

    @Provides
    @Singleton
    fun provideFileRepositroty(
        @ApplicationContext context: Context
    ): FileRepository {
        return FileRepositoryImpl(provideFileDataSource(context))
    }

    @Provides
    fun provideDeletePlaylistUseCase(@ApplicationContext context: Context): DeletePlaylistUseCase {
        return DeletePlaylistUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun provideGetListOfPlaylistsUseCase(@ApplicationContext context: Context): GetListOfPlaylistsUseCase {
        return GetListOfPlaylistsUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun provideGetListUseCase(@ApplicationContext context: Context): GetListUseCase {
        return GetListUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun provideSavePlaylistUseCase(@ApplicationContext context: Context): SavePlaylistUseCase {
        return SavePlaylistUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun getAllFromDatabase(@ApplicationContext context: Context): GetAllFromDatabase {
        return GetAllFromDatabase(provideFileRepositroty(context))
    }

    @Provides
    fun provideGetFileList(@ApplicationContext context: Context): GetFileListUseCase {
        return GetFileListUseCase(provideFileRepositroty(context))
    }

    @Provides
    fun provideInsertDatabase(@ApplicationContext context: Context): InsertDatabaseUseCase {
        return InsertDatabaseUseCase(provideFileRepositroty(context))
    }


}