package com.mobinest.pokeapp.ui.pokedetail.domain

import androidx.lifecycle.viewModelScope
import com.mobinest.pokeapp.core.data.repository.PokeRepository
import com.mobinest.pokeapp.core.model.extension.StringExtension.empty
import com.mobinest.pokeapp.core.model.local.UiState
import com.mobinest.pokeapp.core.model.remote.network.Status
import com.mobinest.pokeapp.core.model.remote.response.PokemonDetailResponse
import com.mobinest.pokeapp.core.uicomponents.platform.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : BaseViewModel<PokemonDetailViewState, PokemonDetailViewAction>(PokemonDetailViewState()) {
    private val _pokemonDetail = MutableStateFlow<PokemonDetailResponse?>(null)
    val pokemonDetail: StateFlow<PokemonDetailResponse?>
        get() = _pokemonDetail

    fun fetchPokemonDetail(pokemonId: Int) = viewModelScope.launch(Dispatchers.IO) {
        sendAction(viewAction = PokemonDetailViewAction.OnLoading)

        val resource = pokeRepository.fetchPokemonDetail(pokemonId = pokemonId).first()

        when (resource.status) {
            Status.SUCCESS -> _pokemonDetail.value = resource.data

            Status.ERROR -> sendAction(viewAction = PokemonDetailViewAction.OnFailure(errorMessage = resource.error?.message))
        }
    }

    fun popBack() {
        sendAction(viewAction = PokemonDetailViewAction.PopBack)
    }

    override fun onReduceState(viewAction: PokemonDetailViewAction): PokemonDetailViewState =
        when (viewAction) {
            is PokemonDetailViewAction.OnFailure -> state.copy(
                uiState = UiState.ERROR, errorMessage = viewAction.errorMessage
            )

            PokemonDetailViewAction.OnLoading -> state.copy(
                uiState = UiState.LOADING, errorMessage = String.empty
            )

            PokemonDetailViewAction.PopBack -> state.copy(
                uiState = UiState.SUCCESS, errorMessage = String.empty, shouldPopBack = true
            )
        }
}
