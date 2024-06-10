package com.example.recyclerview.models

data class Movie(
    val kinopoiskId: Int,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val genres: List<Genre>,
    val countries: List<Country>
)

data class Genre(
    val genre: String
)

data class Country(
    val country: String
)