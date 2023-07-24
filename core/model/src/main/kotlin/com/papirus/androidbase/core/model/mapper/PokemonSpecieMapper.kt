package com.papirus.androidbase.core.model.mapper

import com.papirus.androidbase.core.model.extension.CollectionExtension.safeGet
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.SLASH

object PokemonSpecieMapper {
    fun List<PokemonSpecieResponse.PokemonSpecie>.toEntity(): List<PokemonSpecieResponse.PokemonSpecie> =
        map {
            try {
                // Getting exact id from the url
                val iterations = it.url.split(SLASH)
                val pokemonId = iterations.safeGet(index = iterations.size.minus(2))

                it.id = pokemonId?.toInt() ?: 0

                it
            } catch (e: Exception) {
                it
            }
        }
}