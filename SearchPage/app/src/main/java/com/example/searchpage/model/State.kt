package com.example.searchpage.model

sealed class State {
    object Initialization: State()
    object Loading: State()
    object Success: State()

    data class Error(
        val requestError: String
    ): State()
}