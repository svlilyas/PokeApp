package com.papirus.androidbase.ui.pokelist.domain

import com.papirus.androidbase.core.model.extension.StringExtension.empty
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.uicomponents.platform.viewmodel.BaseAction

sealed class PokemonListViewAction : BaseAction {
    data class OnFailure(val errorMessage: String? = String.empty) : PokemonListViewAction()
    object OnLoading : PokemonListViewAction()
    data class OnFetchPokemonListSuccess(val pokemonList:List<PokemonSpecieResponse.PokemonSpecie>? = emptyList()) : PokemonListViewAction()
}