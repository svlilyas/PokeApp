package com.papirus.androidbase.core.data.repository

import androidx.annotation.WorkerThread
import com.papirus.androidbase.core.data.client.PokeClient
import com.papirus.androidbase.core.database.db.PokeDao
import com.papirus.androidbase.core.model.extension.CollectionExtension.safeGet
import com.papirus.androidbase.core.model.remote.network.Resource
import com.papirus.androidbase.core.model.remote.response.ErrorResponse
import com.papirus.androidbase.core.model.remote.response.PokemonDetailResponse
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.IS_NETWORK_FETCH
import com.papirus.androidbase.core.model.utils.JsonSerializer.toObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val pokeClient: PokeClient,
    private val pokeDao: PokeDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    @WorkerThread
    suspend fun fetchPokemonList(): Flow<Resource<PokemonSpecieResponse>> = flow {
        val pokemonList = pokeDao.getPokemonListWithOffset(20)

        /**
         * Getting data from Room database if not then fetch from network
         */

        if (!pokemonList.isNullOrEmpty() && !IS_NETWORK_FETCH) {
            emit(Resource.success(data = PokemonSpecieResponse(results = pokemonList)))
        } else {
            val pokemonResponse =
                pokeClient.fetchPokemonList(offset = 0, limit = 20).handleResponse().first().data

            pokemonResponse?.results?.map {
                val iterations = it.url.split("/")
                val pokemonId = iterations.safeGet(index = iterations.size.minus(2))

                it.id = pokemonId?.toInt() ?: 0
            }

            pokeDao.upsertAll(pokemonSpecies = pokemonResponse?.results.orEmpty())

            emit(Resource.success(data = pokemonResponse))
        }
    }

    @WorkerThread
    suspend fun fetchPokemonDetail(
        pokemonId: Int
    ): Flow<Resource<PokemonDetailResponse>> =
        pokeClient.fetchPokemonDetail(pokemonId = pokemonId).handleResponse()

    private fun <T> Response<T>.handleResponse(): Flow<Resource<T>> = flow {
        if (isSuccessful) {
            emit(Resource.success(data = body()))
        } else {
            // TODO Need to add custom error messages
            /**
             * Could be handled all network errors here
             */

            emit(
                Resource.error(
                    error = errorBody()?.string()?.toObject() ?: ErrorResponse(
                        errorCode = code(), message = errorBody()?.string()
                    )
                )
            )
        }
    }.flowOn(ioDispatcher)
}
