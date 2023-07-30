package com.papirus.androidbase.core.model.utils

class AppConstants {
    companion object {
        const val POKEMON_IMAGE_URL_PATTERN =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%1s.png"

        /**
         * STRINGS
         */
        const val STRING_EMPTY: String = ""

        const val POKE_PAGE_SIZE = 20

        /**
         * Determines whether fetch data from network
         */
        var IS_NETWORK_FETCH = true

        /**
         * DELIMITERS
         */
        const val SLASH = "/"
    }
}