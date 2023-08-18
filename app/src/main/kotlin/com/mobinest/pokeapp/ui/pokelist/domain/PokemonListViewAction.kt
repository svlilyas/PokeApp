package com.mobinest.pokeapp.ui.pokelist.domain

import com.mobinest.pokeapp.core.model.extension.StringExtension.empty
import com.mobinest.pokeapp.core.uicomponents.platform.viewmodel.BaseAction

sealed class PokemonListViewAction : BaseAction {
    data class OnFailure(val errorMessage: String? = String.empty) : PokemonListViewAction()
    object OnLoading : PokemonListViewAction()
}
