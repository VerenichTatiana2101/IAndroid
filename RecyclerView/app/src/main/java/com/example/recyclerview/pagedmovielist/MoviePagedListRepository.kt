package com.example.recyclerview.pagedmovielist

import com.example.recyclerview.api.retrofit
import com.example.recyclerview.models.Movie

class MoviePagedListRepository {
    suspend fun getTopList(page: Int): List<Movie>{
        return retrofit.topList(page).films
    }
}