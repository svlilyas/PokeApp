package com.papirus.androidbase.ui.pokelist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.POKEMON_IMAGE_URL
import com.papirus.androidbase.core.uicomponents.extensions.ImageViewExtension.loadImage
import com.papirus.androidbase.core.uicomponents.platform.BaseListAdapter
import com.papirus.androidbase.core.uicomponents.platform.BaseViewHolder
import com.papirus.androidbase.databinding.ItemPokemonSpecieBinding

class PokemonAdapter :
    BaseListAdapter<PokemonSpecieResponse.PokemonSpecie>(itemsSame = { old, new -> old.id == new.id },
        contentsSame = { old, new -> old == new }) {

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder = PokemonViewHolder(parent = parent, inflater = inflater)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as PokemonViewHolder).bind(
            pokemon = getItem(position)
        )

    override fun getItemCount(): Int = currentList.size

    private inner class PokemonViewHolder(
        parent: ViewGroup, inflater: LayoutInflater
    ) : BaseViewHolder<ItemPokemonSpecieBinding>(
        binding = ItemPokemonSpecieBinding.inflate(inflater, parent, false)
    ) {
        fun bind(pokemon: PokemonSpecieResponse.PokemonSpecie) {
            with(binding) {
                ivPokemon.loadImage(imageSource = POKEMON_IMAGE_URL.format(pokemon.name))

                tvPokemonName.text = pokemon.name
            }
        }
    }
}