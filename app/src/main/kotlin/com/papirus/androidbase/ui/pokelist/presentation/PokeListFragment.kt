package com.papirus.androidbase.ui.pokelist.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.papirus.androidbase.R
import com.papirus.androidbase.core.model.local.UiState
import com.papirus.androidbase.core.uicomponents.binding.BindingFragment
import com.papirus.androidbase.core.uicomponents.binding.ViewExtension.invisible
import com.papirus.androidbase.core.uicomponents.binding.ViewExtension.visible
import com.papirus.androidbase.core.uicomponents.extensions.FlowExt.flowWithLifecycle
import com.papirus.androidbase.core.uicomponents.extensions.FragmentExt.toastMessage
import com.papirus.androidbase.databinding.FragmentPokelistBinding
import com.papirus.androidbase.ui.pokelist.domain.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
        binding {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            pokemonAdapter = PokemonAdapter()
            rvPokemonList.adapter = pokemonAdapter

            swPokemonList.setOnRefreshListener {
                pokemonAdapter?.refresh()
            }

            pokemonAdapter?.setOnDebouncedClickListener { pokemon ->
                findNavController().navigate(
                    PokeListFragmentDirections.actionPokeListFragmentToPokeDetailFragment(
                        pokemon.id
                    )
                )
            }

            setOnSearchQueryChange()
        }
    }

    private fun setOnSearchQueryChange() {
        binding.svPokemon.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.filterPages(query = it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.filterPages(query = it)
                }
                return true
            }

        })
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

                    binding.tvNoData.visible(isVisible = uiState == UiState.ERROR)
                }
            }
        }

        flowWithLifecycle(flowData = viewModel.pokemonPagingFlow) {
            pokemonAdapter?.submitData(it)

            binding.swPokemonList.isRefreshing = false
        }

        flowWithLifecycle(flowData = pokemonAdapter?.loadStateFlow) {
            val state = it.refresh

            withContext(Dispatchers.Main) {
                if (state is LoadState.Loading) {
                    showShimmerLoading()
                } else {
                    hideShimmerLoading()
                }
            }
        }
    }

    private fun showShimmerLoading() {
        binding {
            shimmerLayout.visible()
            shimmerLayout.startShimmer()
            swPokemonList.invisible()
        }
    }

    private fun hideShimmerLoading() {
        binding {
            shimmerLayout.invisible()
            shimmerLayout.stopShimmer()
            swPokemonList.visible()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pokemonAdapter = null
    }
}