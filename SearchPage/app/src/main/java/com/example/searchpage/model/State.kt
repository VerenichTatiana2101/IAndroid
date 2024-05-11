package com.example.searchpage.model

sealed class State {
    object Loading: State()
    object Success: State()

    data class Error(
        val requestError: String?
    ): State()
}