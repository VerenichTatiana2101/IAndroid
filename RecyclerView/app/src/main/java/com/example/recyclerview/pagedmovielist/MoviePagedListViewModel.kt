package com.example.recyclerview.pagedmovielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.recyclerview.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MoviePagedListViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    val filterEnabled = MutableStateFlow(false)

    val pageMovies = Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {MoviePagingSource()}
    ).flow.cachedIn(viewModelScope)


    private val _movies = MutableStateFlow<List<Movie>>(emptyList())

    val movies: StateFlow<List<Movie>> = combine(_movies, filterEnabled) { movies, filterEnabled ->
        if (filterEnabled)
            movies.filter { movie ->
                movie.countries.any { it.country == "Russia" }
            }
        else movies
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        _movies.value
    )
}
