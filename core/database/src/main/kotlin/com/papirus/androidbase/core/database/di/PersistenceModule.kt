package com.papirus.androidbase.core.database.di

import android.app.Application
import androidx.room.Room
import com.papirus.androidbase.core.database.db.PokeDao
import com.papirus.androidbase.core.database.db.PokeDatabase
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
    ): PokeDatabase = Room.databaseBuilder(application, PokeDatabase::class.java, "Poke.db")
        .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providePokeDao(pokeDatabase: PokeDatabase): PokeDao = pokeDatabase.pokeDao()
}
