package com.papirus.androidbase.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.papirus.androidbase.core.data.repository.PokeRepository
import com.papirus.androidbase.core.model.remote.response.PokemonSpecieResponse
import kotlinx.coroutines.flow.first
import retrofit2.HttpException

class PokePagingSource(private val pokeRepository: PokeRepository) :
    PagingSource<Int, PokemonSpecieResponse.PokemonSpecie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonSpecieResponse.PokemonSpecie> {
        return try {
            val currentPage = params.key ?: 1
            val response = pokeRepository.fetchPokemonList().first()
            val data = response.data?.results
            val responseData = mutableListOf<PokemonSpecieResponse.PokemonSpecie>()
            responseData.addAll(data ?: emptyList())

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonSpecieResponse.PokemonSpecie>): Int? {
        return null
    }
}