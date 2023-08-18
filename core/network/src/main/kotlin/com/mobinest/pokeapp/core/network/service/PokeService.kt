package com.mobinest.pokeapp.core.network.service

import com.mobinest.pokeapp.core.model.remote.response.PokemonDetailResponse
import com.mobinest.pokeapp.core.model.remote.response.PokemonSpecieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {
    object Endpoint {
        const val mainPath = "api/v2"

        object PokemonSpecie {
            const val pokemonList = "$mainPath/pokemon-species"
            const val pokemonDetail = "$mainPath/pokemon-species/{pokemonId}"
        }
    }

    @GET(Endpoint.PokemonSpecie.pokemonList)
    suspend fun fetchPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonSpecieResponse>

    @GET(Endpoint.PokemonSpecie.pokemonDetail)
    suspend fun fetchPokemonDetail(@Path("pokemonId") pokemonId: String): Response<PokemonDetailResponse>
}
