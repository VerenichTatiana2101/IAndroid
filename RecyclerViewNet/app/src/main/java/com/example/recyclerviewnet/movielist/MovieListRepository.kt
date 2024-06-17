package com.example.recyclerviewnet.movielist

import com.example.recyclerviewnet.api.retrofit
import com.example.recyclerviewnet.models.Movie

// вызывает Api для загрузки списка фильмов, который вызывается view моделью
class MovieListRepository {
    suspend fun getPremieres(year: Int, month: String): List<Movie> {
        return retrofit.movies(year, month).items
    }
}