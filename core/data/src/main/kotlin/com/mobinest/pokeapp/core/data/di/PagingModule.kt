package com.mobinest.pokeapp.core.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mobinest.pokeapp.core.data.client.PokeClient
import com.mobinest.pokeapp.core.data.mediator.PokeRemoteMediator
import com.mobinest.pokeapp.core.database.db.PokeDatabase
import com.mobinest.pokeapp.core.model.remote.response.PokemonSpecieResponse
import com.mobinest.pokeapp.core.model.utils.AppConstants.Companion.POKE_PAGE_SIZE
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
            initialLoadSize = POKE_PAGE_SIZE,
            enablePlaceholders = true
        ),
        remoteMediator = PokeRemoteMediator(
            pokeClient = pokeClient, pokeDatabase = pokeDatabase
        ),
        pagingSourceFactory = {
            pokeDatabase.pokeDao().pagingSource()
        }
    )
}
