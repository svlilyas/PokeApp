package com.papirus.androidbase.core.database.db

import androidx.room.*
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse

@Dao
interface AppDao {
    /**
     * Pokemon Specie Operations
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonSpecie: PokemonSpecieResponse.PokemonSpecie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonSpecies: List<PokemonSpecieResponse.PokemonSpecie>)

    @Query("SELECT * FROM pokemon_specie_db")
    suspend fun getPokemonList(): List<PokemonSpecieResponse.PokemonSpecie>?

    @Query("Delete From pokemon_specie_db")
    suspend fun removeAllPokemonSpecies()
}
