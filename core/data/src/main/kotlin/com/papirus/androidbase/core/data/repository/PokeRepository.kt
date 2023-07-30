package com.papirus.androidbase.core.data.repository

import androidx.annotation.WorkerThread
import com.papirus.androidbase.core.data.client.PokeClient
import com.papirus.androidbase.core.model.remote.network.Resource
import com.papirus.androidbase.core.model.remote.response.PokemonDetailResponse
import com.papirus.androidbase.core.network.utils.NetworkHandler.handleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val pokeClient: PokeClient, private val ioDispatcher: CoroutineDispatcher
) {
    @WorkerThread
    suspend fun fetchPokemonDetail(
        pokemonId: Int
    ): Flow<Resource<PokemonDetailResponse>> =
        handleResponse(invokeRequest = {
            pokeClient.fetchPokemonDetail(pokemonId = pokemonId)
        }).flowOn(ioDispatcher)
}
