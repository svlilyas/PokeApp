package com.mobinest.pokeapp.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobinest.pokeapp.core.model.remote.response.PokemonSpecieResponse

@Database(
    entities = [PokemonSpecieResponse.PokemonSpecie::class],
    version = 8,
    exportSchema = false
)
abstract class PokeDatabase : RoomDatabase() {

    abstract fun pokeDao(): PokeDao
}
