package com.papirus.androidbase.core.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.papirus.androidbase.core.data.client.PokeClient
import com.papirus.androidbase.core.database.db.PokeDatabase
import com.papirus.androidbase.core.model.mapper.PokemonSpecieMapper.toEntity
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.IS_NETWORK_FETCH
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.POKE_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokeRemoteMediator @Inject constructor(
    private val pokeClient: PokeClient,
    private val pokeDatabase: PokeDatabase
) : RemoteMediator<Int, PokemonSpecieResponse.PokemonSpecie>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonSpecieResponse.PokemonSpecie>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        (lastItem.id / state.config.pageSize).plus(1)
                    }
                }
            }

            val pokemonSpecies =
                pokeClient.fetchPokemonList(
                    offset = loadKey * POKE_PAGE_SIZE,
                    limit = POKE_PAGE_SIZE
                ).body()?.results ?: emptyList()

            pokeDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokeDatabase.pokeDao().clearAll()
                }
                pokeDatabase.pokeDao().upsertAll(pokemonSpecies.toEntity())
            }

            MediatorResult.Success(
                endOfPaginationReached = pokemonSpecies.isEmpty() || !IS_NETWORK_FETCH
            )
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }
}