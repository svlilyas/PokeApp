package com.mobinest.pokeapp.ui.pokedetail.domain

import com.mobinest.pokeapp.core.uicomponents.platform.viewmodel.BaseAction

sealed class PokemonDetailViewAction : BaseAction {
    data class OnFailure(val errorMessage: String?) : PokemonDetailViewAction()
    object OnLoading : PokemonDetailViewAction()
    object PopBack : PokemonDetailViewAction()
}
