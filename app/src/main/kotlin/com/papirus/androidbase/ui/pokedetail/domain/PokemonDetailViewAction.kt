package com.papirus.androidbase.ui.pokedetail.domain

import com.papirus.androidbase.core.uicomponents.platform.viewmodel.BaseAction

sealed class PokemonDetailViewAction : BaseAction {
    data class OnFailure(val errorMessage: String?) : PokemonDetailViewAction()
    object OnLoading : PokemonDetailViewAction()
    object PopBack : PokemonDetailViewAction()
}
