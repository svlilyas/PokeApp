package com.papirus.androidbase.core.database.di

import android.app.Application
import androidx.room.Room
import com.papirus.androidbase.core.database.db.AppDao
import com.papirus.androidbase.core.database.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "App.db")
        .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase): AppDao = appDatabase.appDao()
}
