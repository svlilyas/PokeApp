package com.papirus.androidbase.ui.pokelist.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.papirus.androidbase.R
import com.papirus.androidbase.core.uicomponents.binding.BindingFragment
import com.papirus.androidbase.core.uicomponents.binding.ViewExtension.gone
import com.papirus.androidbase.core.uicomponents.binding.ViewExtension.visible
import com.papirus.androidbase.core.uicomponents.extensions.FragmentExtension.toastMessage
import com.papirus.androidbase.core.uicomponents.extensions.observe
import com.papirus.androidbase.databinding.FragmentPokelistBinding
import com.papirus.androidbase.ui.pokelist.domain.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeListFragment : BindingFragment<FragmentPokelistBinding>(R.layout.fragment_pokelist) {

    private val viewModel by viewModels<PokemonListViewModel>()

    private var pokemonAdapter: PokemonAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        observeData()
    }

    private fun initUI() {
        with(binding) {
            pokemonAdapter = PokemonAdapter()
            rvPokemonList.adapter = pokemonAdapter
        }
    }

    /**
     * Observing View Events and noteList to fill the View
     */
    private fun observeData() {

        observe(viewModel.uiStateFlow) { viewState ->
            with(viewState) {
                if (!errorMessage.isNullOrEmpty()) {
                    toastMessage(message = errorMessage)
                }

                if (!pokemonList.isNullOrEmpty()) {
                    pokemonAdapter?.submitList(pokemonList)
                    binding.rvPokemonList.visible()
                } else {
                    binding.rvPokemonList.gone()
                    binding.tvNoData.visible()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pokemonAdapter = null
    }
}