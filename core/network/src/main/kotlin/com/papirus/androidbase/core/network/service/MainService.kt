package com.papirus.androidbase.core.network.service

import com.papirus.androidbase.core.model.remote.response.PokemonDetailResponse
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {
    object Endpoint {
        const val mainPath = "api/v2"

        object PokemonSpecie {
            const val pokemonList = "$mainPath/pokemon-species"
            const val pokemonDetail = "$mainPath/pokemon-species/{pokemonName}"
        }
    }

    @GET(Endpoint.PokemonSpecie.pokemonList)
    suspend fun fetchPokemonList(): Response<PokemonSpecieResponse>

    @GET(Endpoint.PokemonSpecie.pokemonDetail)
    suspend fun fetchPokemonDetail(@Path("pokemonName") pokemonName: String): Response<PokemonDetailResponse>
}
