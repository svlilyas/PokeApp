package com.papirus.androidbase.ui.pokelist.domain

import com.papirus.androidbase.core.model.extension.StringExtension.empty
import com.papirus.androidbase.core.model.local.UiState
import com.papirus.androidbase.core.uicomponents.platform.viewmodel.BaseViewState

data class PokemonListViewState(
    val errorMessage: String? = String.empty,
    override val uiState: UiState = UiState.LOADING
) : BaseViewState
