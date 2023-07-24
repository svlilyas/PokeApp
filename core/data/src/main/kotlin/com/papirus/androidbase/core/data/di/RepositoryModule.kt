package com.papirus.androidbase.core.data.di

import com.papirus.androidbase.core.data.client.PokeClient
import com.papirus.androidbase.core.data.repository.PokeRepository
import com.papirus.androidbase.core.database.db.PokeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokeRepository(
        pokeClient: PokeClient,
        pokeDao: PokeDao,
        ioDispatcher: CoroutineDispatcher
    ): PokeRepository =
        PokeRepository(pokeClient = pokeClient, pokeDao = pokeDao, ioDispatcher = ioDispatcher)
}
