package com.mobinest.pokeapp.ui.pokelist.domain

import com.mobinest.pokeapp.core.model.extension.StringExtension.empty
import com.mobinest.pokeapp.core.model.local.UiState
import com.mobinest.pokeapp.core.uicomponents.platform.viewmodel.BaseViewState

data class PokemonListViewState(
    val errorMessage: String? = String.empty,
    override val uiState: UiState = UiState.LOADING
) : BaseViewState
