package com.example.recyclerviewnet.pagedmovielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.recyclerviewnet.models.Movie
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

//////////// в данном случае лучше фильтровать при запросе данных из сервера
    /*private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    // при нажатии фильтрует список только рос.фильмов
    val movies: StateFlow<List<Movie>> = combine(_movies, filterEnabled) { movies, filterEnabled ->
        if (filterEnabled)
            movies.filter { movie ->
                movie.countries.any { it.country == "Россия" }
            }
        else movies
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _movies.value
    )*/

    val pagedMovies: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 10), // кол-во подгрузки
        pagingSourceFactory = { MoviePagingSource() }
    ).flow.cachedIn(viewModelScope) // для сохранения данных во вью модели, у Pager специальный оператор cachedIn



}
