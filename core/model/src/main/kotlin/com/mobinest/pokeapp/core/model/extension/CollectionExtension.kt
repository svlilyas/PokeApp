package com.mobinest.pokeapp.core.model.extension

object CollectionExtension {
    fun <T> List<T>?.safeGet(index: Int): T? = if (!isNullOrEmpty() && size > index) {
        get(index)
    } else {
        null
    }
}