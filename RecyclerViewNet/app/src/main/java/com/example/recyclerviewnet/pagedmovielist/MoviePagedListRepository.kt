package com.example.recyclerviewnet.pagedmovielist

import com.example.recyclerviewnet.api.retrofit
import com.example.recyclerviewnet.models.Movie
import kotlinx.coroutines.delay

class MoviePagedListRepository {
    suspend fun getTopList(page: Int): List<Movie> {
        //delay(2000)
        return retrofit.topList(page).films
    }
}
