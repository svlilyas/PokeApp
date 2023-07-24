package com.papirus.androidbase.ui.pokelist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import com.papirus.androidbase.core.model.utils.AppConstants.Companion.POKEMON_IMAGE_URL_PATTERN
import com.papirus.androidbase.core.uicomponents.extensions.ImageViewExt.loadImage
import com.papirus.androidbase.core.uicomponents.extensions.ViewExt.setOnDebouncedClickListener
import com.papirus.androidbase.core.uicomponents.platform.BaseViewHolder
import com.papirus.androidbase.databinding.ItemPokemonSpecieBinding

class PokemonAdapter :
    PagingDataAdapter<PokemonSpecieResponse.PokemonSpecie, PokemonAdapter.PokemonViewHolder>(
        differCallBack
    ) {
    var debouncedClickListener: ((item: PokemonSpecieResponse.PokemonSpecie) -> Unit)? = null

    fun setOnDebouncedClickListener(listener: (item: PokemonSpecieResponse.PokemonSpecie) -> Unit) {
        debouncedClickListener = listener
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.let {
            return holder.bind(
                pokemon = it
            )
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(parent = parent)

    inner class PokemonViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder<ItemPokemonSpecieBinding>(
        binding = ItemPokemonSpecieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ) {
        fun bind(pokemon: PokemonSpecieResponse.PokemonSpecie) {
            with(binding) {
                ivPokemon.loadImage(imageSource = POKEMON_IMAGE_URL_PATTERN.format(pokemon.id))

                tvPokemonName.text = pokemon.name

                root.setOnDebouncedClickListener {
                    debouncedClickListener?.invoke(pokemon)
                }
            }
        }
    }

    companion object {
        private val differCallBack =
            object : DiffUtil.ItemCallback<PokemonSpecieResponse.PokemonSpecie>() {
                override fun areItemsTheSame(
                    oldItem: PokemonSpecieResponse.PokemonSpecie,
                    newItem: PokemonSpecieResponse.PokemonSpecie
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: PokemonSpecieResponse.PokemonSpecie,
                    newItem: PokemonSpecieResponse.PokemonSpecie
                ) = oldItem == newItem
            }
    }
}