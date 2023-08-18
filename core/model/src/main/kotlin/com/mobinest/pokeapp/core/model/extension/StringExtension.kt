package com.mobinest.pokeapp.core.model.extension

import com.mobinest.pokeapp.core.model.utils.AppConstants.Companion.STRING_EMPTY

object StringExtension {
    val String.Companion.empty: String
        inline get() = STRING_EMPTY

    fun Any?.toStringOrNull(): String? = this?.toString()
}