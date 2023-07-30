package com.papirus.androidbase.ui.pokelist.domain

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.filter
import com.papirus.androidbase.core.model.extension.StringExtension.empty
import com.papirus.androidbase.core.model.local.UiState
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.IS_NETWORK_FETCH
import com.papirus.androidbase.core.uicomponents.platform.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    pager: Pager<Int, PokemonSpecieResponse.PokemonSpecie>
) : BaseViewModel<PokemonListViewState, PokemonListViewAction>(PokemonListViewState()) {
    private val _searchStateFlow = MutableStateFlow(String.empty)

    @OptIn(FlowPreview::class)
    val pokemonPagingFlow =
        pager.flow.cachedIn(viewModelScope)
            .combine(_searchStateFlow.debounce(300)) { pagerData, query ->
                IS_NETWORK_FETCH = query == String.empty
                pagerData.filter { it.name.contains(other = query, ignoreCase = true) }
            }

    override fun onReduceState(viewAction: PokemonListViewAction): PokemonListViewState =
        when (viewAction) {
            is PokemonListViewAction.OnFailure -> state.copy(
                uiState = UiState.ERROR, errorMessage = viewAction.errorMessage
            )

            PokemonListViewAction.OnLoading -> state.copy(
                uiState = UiState.LOADING, errorMessage = String.empty
            )
        }

    fun filterPages(query: String) {
        _searchStateFlow.value = query
    }
}
