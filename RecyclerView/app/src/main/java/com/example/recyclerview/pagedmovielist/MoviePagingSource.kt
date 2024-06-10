package com.example.recyclerview.pagedmovielist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recyclerview.models.Movie

class MoviePagingSource : PagingSource<Int, Movie>() {
    private val repository = MoviePagedListRepository()

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = FIRST_PAGE

    // реализовать загрузку данных из api и врнуть с помощью объкта loadpage
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getTopList(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {LoadResult.Error(it)}
        )
    }

    private companion object{
        private val  FIRST_PAGE = 1
    }
}