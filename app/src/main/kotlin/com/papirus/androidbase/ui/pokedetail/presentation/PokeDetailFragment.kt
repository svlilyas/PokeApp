package com.papirus.androidbase.ui.pokedetail.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.papirus.androidbase.R
import com.papirus.androidbase.core.model.local.UiState
import com.papirus.androidbase.core.uicomponents.binding.BindingFragment
import com.papirus.androidbase.core.uicomponents.binding.ViewExtension.visible
import com.papirus.androidbase.core.uicomponents.extensions.FlowExt.flowWithLifecycle
import com.papirus.androidbase.core.uicomponents.extensions.FragmentExt.toastMessage
import com.papirus.androidbase.databinding.FragmentPokemonDetailBinding
import com.papirus.androidbase.ui.pokedetail.domain.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class PokeDetailFragment :
    BindingFragment<FragmentPokemonDetailBinding>(R.layout.fragment_pokemon_detail) {
    private val viewModel by viewModels<PokemonDetailViewModel>()

    private val args by navArgs<PokeDetailFragmentArgs>()

    private val pokemonId by lazy { args.pokemonId }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.lifecycleOwner = this

        fetchData()

        observeData()
    }

    private fun fetchData() {
        viewModel.fetchPokemonDetail(pokemonId = pokemonId)
    }

    /**
     * Observing View Events and noteList to fill the View
     */
    private fun observeData() {
        flowWithLifecycle(flowData = viewModel.uiStateFlow) { viewState ->
            withContext(Dispatchers.Main) {
                with(viewState) {
                    if (!errorMessage.isNullOrEmpty()) {
                        toastMessage(message = errorMessage)
                    }

                    if (shouldPopBack) {
                        findNavController().popBackStack()
                    }

                    binding.progressBar.visible(isVisible = uiState == UiState.LOADING)

                    binding.tvNoData.visible(isVisible = uiState == UiState.ERROR)
                }
            }
        }
    }
}