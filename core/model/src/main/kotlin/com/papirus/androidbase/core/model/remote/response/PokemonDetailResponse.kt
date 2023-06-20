package com.papirus.androidbase.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailResponse(
    @Json(name = "base_happiness")
    val baseHappiness: Int?,
    @Json(name = "capture_rate")
    val captureRate: Int?,
    @Json(name = "color")
    val color: Color?,
    @Json(name = "egg_groups")
    val eggGroups: List<EggGroup?>?,
    @Json(name = "evolution_chain")
    val evolutionChain: EvolutionChain?,
    @Json(name = "evolves_from_species")
    val evolvesFromSpecies: EvolvesFromSpecies?,
    @Json(name = "flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry?>?,
    @Json(name = "form_descriptions")
    val formDescriptions: List<FormDescription?>?,
    @Json(name = "forms_switchable")
    val formsSwitchable: Boolean?,
    @Json(name = "gender_rate")
    val genderRate: Int?,
    @Json(name = "genera")
    val genera: List<Genera?>?,
    @Json(name = "generation")
    val generation: Generation?,
    @Json(name = "growth_rate")
    val growthRate: GrowthRate?,
    @Json(name = "habitat")
    val habitat: Any?,
    @Json(name = "has_gender_differences")
    val hasGenderDifferences: Boolean?,
    @Json(name = "hatch_counter")
    val hatchCounter: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "is_baby")
    val isBaby: Boolean?,
    @Json(name = "is_legendary")
    val isLegendary: Boolean?,
    @Json(name = "is_mythical")
    val isMythical: Boolean?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "names")
    val names: List<Name?>?,
    @Json(name = "order")
    val order: Int?,
    @Json(name = "pal_park_encounters")
    val palParkEncounters: List<Any?>?,
    @Json(name = "pokedex_numbers")
    val pokedexNumbers: List<PokedexNumber?>?,
    @Json(name = "shape")
    val shape: Shape?,
    @Json(name = "varieties")
    val varieties: List<Variety?>?
) {
    @JsonClass(generateAdapter = true)
    data class Color(
        @Json(name = "name")
        val name: String?,
        @Json(name = "url")
        val url: String?
    )

    @JsonClass(generateAdapter = true)
    data class EggGroup(
        @Json(name = "name")
        val name: String?,
        @Json(name = "url")
        val url: String?
    )

    @JsonClass(generateAdapter = true)
    data class EvolutionChain(
        @Json(name = "url")
        val url: String?
    )

    @JsonClass(generateAdapter = true)
    data class EvolvesFromSpecies(
        @Json(name = "name")
        val name: String?,
        @Json(name = "url")
        val url: String?
    )

    @JsonClass(generateAdapter = true)
    data class FlavorTextEntry(
        @Json(name = "flavor_text")
        val flavorText: String?,
        @Json(name = "language")
        val language: Language?,
        @Json(name = "version")
        val version: Version?
    ) {
        @JsonClass(generateAdapter = true)
        data class Language(
            @Json(name = "name")
            val name: String?,
            @Json(name = "url")
            val url: String?
        )

        @JsonClass(generateAdapter = true)
        data class Version(
            @Json(name = "name")
            val name: String?,
            @Json(name = "url")
            val url: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class FormDescription(
        @Json(name = "description")
        val description: String?,
        @Json(name = "language")
        val language: Language?
    ) {
        @JsonClass(generateAdapter = true)
        data class Language(
            @Json(name = "name")
            val name: String?,
            @Json(name = "url")
            val url: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class Genera(
        @Json(name = "genus")
        val genus: String?,
        @Json(name = "language")
        val language: Language?
    ) {
        @JsonClass(generateAdapter = true)
        data class Language(
            @Json(name = "name")
            val name: String?,
            @Json(name = "url")
            val url: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class Generation(
        @Json(name = "name")
        val name: String?,
        @Json(name = "url")
        val url: String?
    )

    @JsonClass(generateAdapter = true)
    data class GrowthRate(
        @Json(name = "name")
        val name: String?,
        @Json(name = "url")
        val url: String?
    )

    @JsonClass(generateAdapter = true)
    data class Name(
        @Json(name = "language")
        val language: Language?,
        @Json(name = "name")
        val name: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Language(
            @Json(name = "name")
            val name: String?,
            @Json(name = "url")
            val url: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class PokedexNumber(
        @Json(name = "entry_number")
        val entryNumber: Int?,
        @Json(name = "pokedex")
        val pokedex: Pokedex?
    ) {
        @JsonClass(generateAdapter = true)
        data class Pokedex(
            @Json(name = "name")
            val name: String?,
            @Json(name = "url")
            val url: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class Shape(
        @Json(name = "name")
        val name: String?,
        @Json(name = "url")
        val url: String?
    )

    @JsonClass(generateAdapter = true)
    data class Variety(
        @Json(name = "is_default")
        val isDefault: Boolean?,
        @Json(name = "pokemon")
        val pokemon: Pokemon?
    ) {
        @JsonClass(generateAdapter = true)
        data class Pokemon(
            @Json(name = "name")
            val name: String?,
            @Json(name = "url")
            val url: String?
        )
    }
}