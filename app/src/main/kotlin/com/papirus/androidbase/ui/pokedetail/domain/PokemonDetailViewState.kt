package com.papirus.androidbase.ui.pokedetail.domain

import com.papirus.androidbase.core.model.extension.StringExtension.empty
import com.papirus.androidbase.core.model.local.UiState
import com.papirus.androidbase.core.model.remote.response.PokemonDetailResponse
import com.papirus.androidbase.core.uicomponents.platform.viewmodel.BaseViewState

data class PokemonDetailViewState(
    val pokemonDetail: PokemonDetailResponse? = null,
    val errorMessage: String? = String.empty,
    override val uiState: UiState = UiState.LOADING
) : BaseViewState
