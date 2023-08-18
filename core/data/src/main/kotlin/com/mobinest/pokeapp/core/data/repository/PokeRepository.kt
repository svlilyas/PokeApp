package com.mobinest.pokeapp.core.data.repository

import androidx.annotation.WorkerThread
import com.mobinest.pokeapp.core.data.client.PokeClient
import com.mobinest.pokeapp.core.model.remote.network.Resource
import com.mobinest.pokeapp.core.model.remote.response.PokemonDetailResponse
import com.mobinest.pokeapp.core.network.utils.NetworkHandler.handleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val pokeClient: PokeClient, private val ioDispatcher: CoroutineDispatcher
) {
    @WorkerThread
    suspend fun fetchPokemonDetail(
        pokemonId: Int
    ): Flow<Resource<PokemonDetailResponse>> =
        handleResponse(request = {
            pokeClient.fetchPokemonDetail(pokemonId = pokemonId)
        }).flowOn(ioDispatcher)
}
