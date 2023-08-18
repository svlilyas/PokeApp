package com.mobinest.pokeapp.core.data.client

import com.mobinest.pokeapp.core.model.remote.response.PokemonDetailResponse
import com.mobinest.pokeapp.core.model.remote.response.PokemonSpecieResponse
import com.mobinest.pokeapp.core.network.service.PokeService
import retrofit2.Response
import javax.inject.Inject

class PokeClient @Inject constructor(
    private val pokeService: PokeService
) {
    suspend fun fetchPokemonList(
        offset: Int, limit: Int
    ): Response<PokemonSpecieResponse> =
        pokeService.fetchPokemonList(offset = offset, limit = limit)

    suspend fun fetchPokemonDetail(
        pokemonId: Int
    ): Response<PokemonDetailResponse> =
        pokeService.fetchPokemonDetail(pokemonId = pokemonId.toString())
}
