package com.papirus.androidbase.core.model.remote.response


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class PokemonSpecieResponse(
    @Json(name = "count") val count: Int? = 0,
    @Json(name = "next") val next: String? = "",
    @Json(name = "previous") val previous: Any? = null,
    @Json(name = "results") val results: List<PokemonSpecie>? = emptyList()
) {

    @Entity(tableName = "pokemon_specie_db")
    @Parcelize
    data class PokemonSpecie(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        @Transient
        var id: Int = 0,
        @Json(name = "name")
        var name: String = "",
        @Json(name = "url")
        val url: String = ""
    ) : Parcelable
}