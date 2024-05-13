package com.example.searchpage.model

sealed class State {
    object Initialization: State()
    object Loading: State()
    data class Success(
        var result: String
    ): State()

    data class Error(
        val requestError: String
    ): State()
}