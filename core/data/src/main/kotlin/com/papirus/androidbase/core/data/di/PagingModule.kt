package com.papirus.androidbase.core.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.papirus.androidbase.core.data.client.PokeClient
import com.papirus.androidbase.core.data.mediator.PokeRemoteMediator
import com.papirus.androidbase.core.database.db.PokeDatabase
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.POKE_PAGE_SIZE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providePokePager(
        pokeClient: PokeClient,
        pokeDatabase: PokeDatabase,
    ): Pager<Int, PokemonSpecieResponse.PokemonSpecie> = Pager(
        config = PagingConfig(
            pageSize = POKE_PAGE_SIZE,
            prefetchDistance = 5,
            initialLoadSize = POKE_PAGE_SIZE
        ),
        remoteMediator = PokeRemoteMediator(
            pokeClient = pokeClient, pokeDatabase = pokeDatabase
        ),
        pagingSourceFactory = {
            pokeDatabase.pokeDao().pagingSource()
        }
    )
}
