package com.papirus.androidbase.ui.pokedetail.domain

import com.papirus.androidbase.core.model.remote.response.PokemonDetailResponse
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.uicomponents.platform.viewmodel.BaseAction

sealed class PokemonDetailViewAction : BaseAction {
    data class OnPokemonDetailSuccess(val pokemonDetail: PokemonDetailResponse?) :
        PokemonDetailViewAction()

    data class OnFailure(val errorMessage: String?) : PokemonDetailViewAction()
    object OnLoading : PokemonDetailViewAction()
}
