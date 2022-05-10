package com.shainurov.englisheg.di.moduls

import android.content.Context
import androidx.room.Room
import com.shainurov.data.datasource.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RoomModule {

    companion object {
        @Provides
        @Singleton
        fun provideRoom(@ApplicationContext applicationContext: Context): AppDatabase {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "englisGE"
            ).build()
            return db
        }
    }
}