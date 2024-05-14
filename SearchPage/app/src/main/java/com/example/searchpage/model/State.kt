package com.example.searchpage.model

sealed class State(
    open val result: String? = null
) {
    data object Initialization : State()
    data class Loading(override val result: String?) : State(result = result)
    data class Success(override val result: String) : State(result = result)
    data class Error(override val result: String) : State(result = result)
}

