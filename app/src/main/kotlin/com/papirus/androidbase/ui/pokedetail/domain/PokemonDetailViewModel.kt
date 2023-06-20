package com.papirus.androidbase.ui.pokedetail.domain

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
class PokemonDetailViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel<PokemonDetailViewState, PokemonDetailViewAction>(PokemonDetailViewState()) {

    private fun fetchPokemonDetail(pokemonName: String) = viewModelScope.launch(Dispatchers.IO) {

        sendAction(viewAction = PokemonDetailViewAction.OnLoading)

        val resource = mainRepository.fetchPokemonDetail(pokemonName = pokemonName).first()

        when (resource.status) {
            Status.SUCCESS -> sendAction(
                viewAction = PokemonDetailViewAction.OnPokemonDetailSuccess(
                    pokemonDetail = resource.data
                )
            )

            Status.ERROR -> sendAction(viewAction = PokemonDetailViewAction.OnFailure(errorMessage = resource.error?.message))
        }
    }

    override fun onReduceState(viewAction: PokemonDetailViewAction): PokemonDetailViewState =
        when (viewAction) {
            is PokemonDetailViewAction.OnFailure -> state.copy(
                uiState = UiState.ERROR, errorMessage = viewAction.errorMessage
            )

            PokemonDetailViewAction.OnLoading -> state.copy(
                uiState = UiState.LOADING, errorMessage = String.empty
            )

            is PokemonDetailViewAction.OnPokemonDetailSuccess -> state.copy(
                uiState = UiState.SUCCESS,
                errorMessage = String.empty,
                pokemonDetail = viewAction.pokemonDetail
            )
        }
}
