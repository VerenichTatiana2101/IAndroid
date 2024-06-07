package com.example.arhitecturehw.presentation

import com.example.arhitecturehw.entity.UsefulActivity


sealed class State(
    open val res: String
) {
    data class Loading(override val res: String) : State(res = res)
    data class Success(override val res: String) : State(res = res)
    data class Error(override val res: String) : State(res = res)
}