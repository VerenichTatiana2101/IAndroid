package com.example.recyclerviewnet.pagedmovielist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recyclerviewnet.models.Movie

class MoviePagingSource : PagingSource<Int, Movie>() {
    private val repository = MoviePagedListRepository()
    /* передаём страцицу, используемую для обновления данных, с самого начала */
    override fun getRefreshKey(
        state: PagingState<Int, Movie>
    ): Int? = FIRST_PAGE

    /*загружает порцию данных, при изначальной загрузке страницы и при scroll*/
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getTopList(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    // текущая страница
                    data = it,
                    // предыдущая
                    prevKey = null,
                    // следующая
                    nextKey = if (it.isEmpty()) null else page + 1 // если null прекращаем запросы
                )
            },
            // состояние ошибки
            onFailure = { LoadResult.Error(it) }
        )
    }

    private companion object {
        // единица, т.к. в данном api kinopoisk нумерация идет с единицы
        private const val FIRST_PAGE = 1
    }
}
