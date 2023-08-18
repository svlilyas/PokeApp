package com.mobinest.pokeapp.core.database.db

import androidx.paging.PagingSource
import androidx.room.*
import com.mobinest.pokeapp.core.model.remote.response.PokemonSpecieResponse

@Dao
interface PokeDao {
    /**
     * Pokemon Specie Operations
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonSpecie: PokemonSpecieResponse.PokemonSpecie)

    @Upsert
    suspend fun upsertAll(pokemonSpecies: List<PokemonSpecieResponse.PokemonSpecie>)

    @Query("SELECT * FROM pokemon_specie_db")
    fun pagingSource(): PagingSource<Int, PokemonSpecieResponse.PokemonSpecie>

    @Query("SELECT * FROM pokemon_specie_db")
    suspend fun getPokemonList(): List<PokemonSpecieResponse.PokemonSpecie>?

    @Query("SELECT * FROM pokemon_specie_db WHERE :offset >= id")
    suspend fun getPokemonListWithOffset(offset: Int): List<PokemonSpecieResponse.PokemonSpecie>?

    @Query("Delete From pokemon_specie_db")
    suspend fun clearAll()
}
