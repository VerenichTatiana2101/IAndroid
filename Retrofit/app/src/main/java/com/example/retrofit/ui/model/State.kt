package com.example.retrofit.ui.model

sealed class State(
    open val result: String? = null,
    open val photo: String? = null
){
    data class Loading(override val result: String) : State(result = result)
    data class Success(
        override val result: String,
        override val photo: String?
    ) : State(
        result = result,
        photo = photo
    )
    data class Error(override val result: String) : State(result = result)
}