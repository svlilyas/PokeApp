package com.papirus.androidbase.core.data.client

import com.papirus.androidbase.core.model.remote.response.PokemonDetailResponse
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.network.service.MainService
import retrofit2.Response
import javax.inject.Inject

class MainClient @Inject constructor(
    private val mainService: MainService
) {

    suspend fun fetchPokemonList(
    ): Response<PokemonSpecieResponse> =
        mainService.fetchPokemonList()

    suspend fun fetchPokemonDetail(
        pokemonName:String
    ): Response<PokemonDetailResponse> =
        mainService.fetchPokemonDetail(pokemonName = pokemonName)
}
