package com.papirus.androidbase.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse

@Database(
    entities = [PokemonSpecieResponse.PokemonSpecie::class],
    version = 8,
    exportSchema = false
)
abstract class PokeDatabase : RoomDatabase() {

    abstract fun pokeDao(): PokeDao
}