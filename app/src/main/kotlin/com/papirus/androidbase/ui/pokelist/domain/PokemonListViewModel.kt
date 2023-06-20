package com.papirus.androidbase.ui.pokelist.domain

import androidx.lifecycle.viewModelScope
import com.papirus.androidbase.core.data.repository.MainRepository
import com.papirus.androidbase.core.model.extension.StringExtension.empty
import com.papirus.androidbase.core.model.local.UiState
import com.papirus.androidbase.core.model.remote.network.Status
import com.papirus.androidbase.core.uicomponents.platform.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel<PokemonListViewState, PokemonListViewAction>(PokemonListViewState()) {

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() = viewModelScope.launch(Dispatchers.IO) {

        sendAction(viewAction = PokemonListViewAction.OnLoading)

        val resource = mainRepository.fetchPokemonList().first()

        when (resource.status) {
            Status.SUCCESS -> sendAction(
                viewAction = PokemonListViewAction.OnFetchPokemonListSuccess(
                    pokemonList = resource.data?.results
                )
            )

            Status.ERROR -> sendAction(viewAction = PokemonListViewAction.OnFailure(errorMessage = resource.error?.message))
        }
    }

    override fun onReduceState(viewAction: PokemonListViewAction): PokemonListViewState =
        when (viewAction) {
            is PokemonListViewAction.OnFailure -> state.copy(
                uiState = UiState.ERROR, errorMessage = viewAction.errorMessage
            )

            PokemonListViewAction.OnLoading -> state.copy(
                uiState = UiState.LOADING, errorMessage = String.empty
            )

            is PokemonListViewAction.OnFetchPokemonListSuccess -> state.copy(
                errorMessage = String.empty,
                uiState = UiState.SUCCESS,
                pokemonList = viewAction.pokemonList
            )
        }
}
