package com.papirus.androidbase.core.data.repository

import androidx.annotation.WorkerThread
import com.papirus.androidbase.core.data.client.MainClient
import com.papirus.androidbase.core.database.db.AppDao
import com.papirus.androidbase.core.model.remote.network.Resource
import com.papirus.androidbase.core.model.remote.response.ErrorResponse
import com.papirus.androidbase.core.model.remote.response.PokemonDetailResponse
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.model.utils.JsonSerializer.toObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainClient: MainClient,
    private val appDb: AppDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    @WorkerThread
    suspend fun fetchPokemonList(
    ): Flow<Resource<PokemonSpecieResponse>> = flow {
        val pokemonList = appDb.getPokemonList()

        /**
         * Getting data from Room database if not then fetch from network
         */

        if (!pokemonList.isNullOrEmpty()) {
            emit(Resource.success(data = PokemonSpecieResponse(results = pokemonList)))
        } else {
            val resource = mainClient.fetchPokemonList().handleResponse().first()
            emit(Resource.success(data = resource.data))
        }
    }

    @WorkerThread
    suspend fun fetchPokemonDetail(
        pokemonName: String
    ): Flow<Resource<PokemonDetailResponse>> =
        mainClient.fetchPokemonDetail(pokemonName = pokemonName).handleResponse()

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
