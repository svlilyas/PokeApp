package com.papirus.androidbase.ui.pokelist.domain

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.papirus.androidbase.core.model.extension.StringExtension.empty
import com.papirus.androidbase.core.model.local.UiState
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.uicomponents.platform.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    pager: Pager<Int, PokemonSpecieResponse.PokemonSpecie>
) : BaseViewModel<PokemonListViewState, PokemonListViewAction>(PokemonListViewState()) {
    val pokemonPagingFlow = pager.flow.cachedIn(viewModelScope)

    override fun onReduceState(viewAction: PokemonListViewAction): PokemonListViewState =
        when (viewAction) {
            is PokemonListViewAction.OnFailure -> state.copy(
                uiState = UiState.ERROR, errorMessage = viewAction.errorMessage
            )

            PokemonListViewAction.OnLoading -> state.copy(
                uiState = UiState.LOADING, errorMessage = String.empty
            )
        }
}
