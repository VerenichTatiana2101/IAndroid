package com.example.recyclerviewnet.api

import com.example.recyclerviewnet.models.MovieList
import com.example.recyclerviewnet.models.PagedMovieList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieListApi {
    // запрос списка премьер
    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/premieres")
    suspend fun movies(@Query("year") year: Int, @Query("month") month: String): MovieList

    // запрос топ 250
    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/top?type=TOP_250_BEST_FILMS")
    suspend fun topList(@Query("page") page: Int): PagedMovieList

    private companion object {
        private const val api_key = "ce6f81de-e746-4a8b-8a79-4a7fe451b75d"
    }
}

val retrofit = Retrofit
    .Builder()
    .client(
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }).build()
    )
    .baseUrl("https://kinopoiskapiunofficial.tech")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(MovieListApi::class.java)

