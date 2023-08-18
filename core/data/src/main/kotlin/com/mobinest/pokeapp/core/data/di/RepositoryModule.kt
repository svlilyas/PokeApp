package com.mobinest.pokeapp.core.data.di

import com.mobinest.pokeapp.core.data.client.PokeClient
import com.mobinest.pokeapp.core.data.repository.PokeRepository
import com.mobinest.pokeapp.core.database.db.PokeDao
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
        PokeRepository(pokeClient = pokeClient, ioDispatcher = ioDispatcher)
}
